package com.yatochk.tiktokdownloader.model.db

import android.os.Build
import android.os.Environment
import com.yatochk.tiktokdownloader.utils.TIK_TOK_FOLBER
import java.io.File
import java.io.FileOutputStream
import java.lang.Long.compare
import java.util.*

class StorageRepository : StorageApi {
    override fun getFiles(): ArrayList<File> {
        val filesList = java.util.ArrayList<File>()
        val targetPath =
            Environment.getExternalStorageDirectory().absolutePath + TIK_TOK_FOLBER
        val targetDirector = File(targetPath)
        val files = targetDirector.listFiles()

        try {
            Arrays.sort(files) { f1, f2 ->
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    compare((f2 as File).lastModified(), (f1 as File).lastModified())
                } else {
                    when {
                        (f1 as File).lastModified() > (f2 as File).lastModified() -> -1
                        f1.lastModified() < f2.lastModified() -> +1
                        else -> 0
                    }
                }
            }
            filesList.addAll(files)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return filesList
    }

    override fun writeFile(data: ByteArray) {
        val fileName = "TikTokVideo_" + UUID.randomUUID().toString().substring(0, 5) + ".mp4"
        val filename = File(Environment.getExternalStorageDirectory().absolutePath + TIK_TOK_FOLBER, fileName)
        val output = FileOutputStream(filename)
        output.write(data)
        output.close()
    }
}