package com.example.autofillsms

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Telephony
import android.telephony.SmsMessage
import android.util.Log
import android.widget.EditText
import kotlin.math.log

class OTPReceiver : BroadcastReceiver() {

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var edtid_otp: EditText
    }

    fun setEditTextOtp(editText: EditText) {
        edtid_otp = editText
    }

    override fun onReceive(p0: Context?, p1: Intent?) {
        Log.d("TAG", "onReceive: ")
        val smsMessages: Array<SmsMessage>? = Telephony.Sms.Intents.getMessagesFromIntent(p1)
        for (smsMessage: SmsMessage in smsMessages!!) {
            val message_body: String = smsMessage.messageBody.toString()
            val getOtp = message_body.split(":")[1]

            Log.d("TAG", "onReceive: $getOtp")
            Log.d("TAG", "onReceive:  ${smsMessage.originatingAddress}")
            Log.d("TAG", "onReceive:  ${smsMessage.displayOriginatingAddress}")
            edtid_otp.setText(getOtp)
        }

    }
}