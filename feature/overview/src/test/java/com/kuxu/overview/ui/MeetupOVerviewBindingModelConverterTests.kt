package com.kuxu.overview.ui

import com.kuxu.overview.ui.bindingmodel.toHoldingTimeString
import org.junit.Assert
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature
import java.util.*

internal class MeetupOVerviewBindingModelConverterTests : Spek({
    Feature("開催日時の文字列を生成することができる") {
        Scenario("Date型で渡す") {
            When("２０１９年１月２日１９：００開催の日時データを与えて処理させてみる") {
                val calender = Calendar.getInstance().apply {
                    set(Calendar.YEAR, 2019)
                    set(Calendar.MONTH, 0)
                    set(Calendar.DAY_OF_YEAR, 2)
                    set(Calendar.HOUR_OF_DAY, 19)
                    set(Calendar.MINUTE, 0)
                }
                val date = calender.time

                val result = date.toHoldingTimeString()
                Assert.assertEquals("2019/01/02 19:00", result)
            }
        }
    }
})