package com.jpmc.makeschedule

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*
import android.app.ProgressDialog




class MainActivity : AppCompatActivity() {

    var PD: ProgressDialog? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var SavingsMap: MutableMap<String, Any> = HashMap()
        var WithdrawalsMap: MutableMap<String, Any> = HashMap()
        var ChequeMap: MutableMap<String, Any> = HashMap()
        var CreateMap: MutableMap<String, Any> = HashMap()

        var ref = Date(Date().year, Date().month, Date().date, 10, 0, 0)

        var i=1

        var myTime = "10:00"
        var newTime = "10:00"

        while (i<=12){
            myTime = newTime
            val df = SimpleDateFormat("HH:mm")
            val d = df.parse(myTime)
            val cal = Calendar.getInstance()
            cal.time = d
            cal.add(Calendar.MINUTE, 30)
            newTime = df.format(cal.time)
            i++
            SavingsMap.put(myTime + "-" + newTime, "0")
        }

        myTime = "10:00"
        newTime = "10:00"
        i = 1

        while (i<=6){
            myTime = newTime
            val df = SimpleDateFormat("HH:mm")
            val d = df.parse(myTime)
            val cal = Calendar.getInstance()
            cal.time = d
            cal.add(Calendar.MINUTE, 60)
            newTime = df.format(cal.time)
            i++
            WithdrawalsMap.put(myTime + "-" + newTime, "0")
        }

        myTime = "10:00"
        newTime = "10:00"
        i = 1

        while (i<=24){
            myTime = newTime
            val df = SimpleDateFormat("HH:mm")
            val d = df.parse(myTime)
            val cal = Calendar.getInstance()
            cal.time = d
            cal.add(Calendar.MINUTE, 15)
            newTime = df.format(cal.time)
            i++
            ChequeMap.put(myTime + "-" + newTime, "0")
        }

        myTime = "10:00"
        newTime = "10:00"
        i = 1

        while (i<=3){
            myTime = newTime
            val df = SimpleDateFormat("HH:mm")
            val d = df.parse(myTime)
            val cal = Calendar.getInstance()
            cal.time = d
            cal.add(Calendar.MINUTE, 120)
            newTime = df.format(cal.time)
            i++
            CreateMap.put(myTime + "-" + newTime, "0")
        }

        var List: MutableMap<String, Any> = HashMap()

        List.put("Savings", SavingsMap)
        List.put("Withdrawal", WithdrawalsMap)
        List.put("Cheques", ChequeMap)
        List.put("Create Account", CreateMap)

        var table: MutableMap<String, Any> = HashMap()

        val cal = Calendar.getInstance()
        cal.time = Date()
        val df = SimpleDateFormat("MMMM d, yyyy")
        var dat = df.format(cal.time)

        table.put(dat, List)

        Gen_Schedule.setOnClickListener {
            var databaseref = FirebaseDatabase.getInstance().getReference()
            databaseref.updateChildren(table)
        }
    }
}
