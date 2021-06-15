package com.dispatch.sunmooncalculator

import SunMoonCalculator
import SunMoonCalculator.Companion.DEG_TO_RAD
import SunMoonCalculator.Companion.RAD_TO_DEG
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    lateinit var resultTextValue:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resultTextValue = findViewById(R.id.resutValue)
        println("SunMoonCalculator test run")
        try {
            val year = 2019
            val month = 4
            val day = 1
            val h = 3
            val m = 28
            val s = 52 // in UT !!!
            val obsLon = 40.64941 * DEG_TO_RAD
            val obsLat = 54.68653 * DEG_TO_RAD // lon is negative to the west
            val smc = SunMoonCalculator(year, month, day, h, m, s, obsLon, obsLat)
            smc.setTwilightMode(SunMoonCalculator.TWILIGHT_MODE.TODAY_UT) // Default is TWILIGHT_MODE.CLOSEST
            smc.setTwilightModeTimeZone(3) // Only for TWILIGHT_MODE.TODAY_LT
            smc.calcSunAndMoon()
            val degSymbol = "\u00b0"
            println("Sun")
            println(" Az:       " + (smc.sun?.azimuth?.times(RAD_TO_DEG)) + degSymbol)
            println(" El:       " + (smc.sun!!.elevation * RAD_TO_DEG) + degSymbol)
            println(" Dist:     " + smc.sun!!.distance + " AU")
            println(" RA:       " + (smc.sun!!.rightAscension * RAD_TO_DEG) + degSymbol)
            println(" DEC:      " + (smc.sun!!.declination * RAD_TO_DEG) + degSymbol)
            println(" Ill:      " + smc.sun!!.illuminationPhase + "%")
            println(" ang.R:    " + (smc.sun!!.angularRadius * RAD_TO_DEG) + degSymbol)


            println("Moon")

            smc.setTwilight(SunMoonCalculator.TWILIGHT.ASTRONOMICAL)
            smc.calcSunAndMoon()
            println("")
            println("Astronomical twilights:")
            println("Sun")
            System.out.println(" Rise:     " + SunMoonCalculator.getDateAsString(smc.sun!!.rise))
            System.out.println(" Set:      " + SunMoonCalculator.getDateAsString(smc.sun!!.set))
            println("Moon")
            System.out.println(" Rise:     " + SunMoonCalculator.getDateAsString(smc.moon!!.rise))
            System.out.println(" Set:      " + SunMoonCalculator.getDateAsString(smc.moon!!.set))


            var resultText = ""
            resultText= " Az:       " + (smc.sun?.azimuth?.times(RAD_TO_DEG)) + degSymbol + "\n" +

                    " El:       " + (smc.sun!!.elevation * RAD_TO_DEG) + degSymbol + "\n" +
                    " Dist:     " + smc.sun!!.distance + " AU" + "\n" +
                    " RA:       " + (smc.sun!!.rightAscension * RAD_TO_DEG) + degSymbol + "\n" +
                    " DEC:      " + (smc.sun!!.declination * RAD_TO_DEG) + degSymbol  + "\n" +
                    " Ill:      " + smc.sun!!.illuminationPhase + "%" + "\n" +
                    " ang.R:    " + (smc.sun!!.angularRadius * RAD_TO_DEG) + degSymbol + "\n" +
                    " Rise:     " + SunMoonCalculator.getDateAsString(smc.sun!!.rise) + "\n" +
                    " Set:      " + SunMoonCalculator.getDateAsString(smc.sun!!.set)


            resultTextValue.setText(resultText)

        } catch (exc: Exception) {
            exc.printStackTrace()
        }

    }
}