package com.app.bookassistant.utils

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat.startActivityForResult
import com.opencsv.CSVReader
import java.io.*


class CSVUtil {
    companion object {
        fun readFile(file: File): StringBuffer {
            val ret = StringBuffer()
            try {
                val reader = CSVReader(file.reader())
                var nextLine = reader.readNext()
                while (nextLine != null) {
                    nextLine.forEach {
                        ret.append("$it ")
                    }
                    nextLine = reader.readNext()
                }
            } catch (e: IOException) {
                logd("Exception reading csv file: $e")
            }
            return ret
        }

        fun selectCSVFile(activity: AppCompatActivity) {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.addCategory(Intent.CATEGORY_OPENABLE)
            intent.type = "text/*"
            startActivityForResult(
                activity,
                Intent.createChooser(intent, "Open CSV file for Book"),
                Constants.SELECT_CSV_FILE,
                null
            )
        }
    }
}