package com.kuxu.settingpage.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.kuxu.settingpage.databinding.ChoosePrefectureDialogBinding
import com.kuxu.settingpage.domain.PrefectureRepository
import com.kuxu.settingpage.domain.PrefectureSelectedUsecase
import com.kuxu.settingpage.item.ChoosePrefectureAdapter
import com.kuxu.settingpage.item.ChoosePrefectureEventListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import kotlin.coroutines.CoroutineContext

internal class ChoosePrefectureDialog : BottomSheetDialogFragment(), CoroutineScope {

    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    private val prefectureRepository: PrefectureRepository by inject()
    private val prefectureSelectedUsecase: PrefectureSelectedUsecase by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = ChoosePrefectureDialogBinding.inflate(
            inflater,
            container,
            false
        )

        context?.let {

            val adapter = ChoosePrefectureAdapter(it)
            binding.prefectureRecyclerView.adapter = adapter

            adapter.onclickListener = object : ChoosePrefectureEventListener {
                override fun onClick(id: Int, newValue: Boolean) {
                    launch {
                        try {
                            prefectureSelectedUsecase.updateChooseStatus(
                                id,
                                newValue
                            )
                        } catch (e: java.lang.Exception) {
                            dismiss()
                        } finally {
                            updatePrefectureList(adapter)
                        }
                    }
                }
            }

            binding.prefectureRecyclerView.layoutManager = LinearLayoutManager(context)
            updatePrefectureList(adapter)
        }

        return binding.root
    }

    override fun dismiss() {
        super.dismiss()
        job.cancel()
    }

    private fun updatePrefectureList(adapter: ChoosePrefectureAdapter) {
        launch {
            try {
                adapter.submitList(
                    prefectureRepository.loadPrefectureList()
                )
            } catch (e: Exception) {
                dismiss()
            }
        }
    }
}