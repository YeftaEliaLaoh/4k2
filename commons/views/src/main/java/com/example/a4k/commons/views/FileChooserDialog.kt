package com.example.a4k.commons.views

import android.app.Dialog
import android.content.Context
import android.os.Environment
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager.LayoutParams;
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import java.io.File
import java.util.*

class FileChooserDialog(
    context: Context
) : Dialog(context) {

    val PARENT_DIR = ".."
    var list = ListView(context)
    lateinit var currentPath: File
    private val extension: String? = null

    override fun show() {
        super.show()
        list.setOnItemClickListener { _, _, position: Int, _ ->
            val fileChosen = list.getItemAtPosition(position) as String
            val chosenFile: File = getChosenFile(fileChosen)
            refresh(chosenFile)
        };
        setContentView(list);
        window?.setLayout(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        refresh(Environment.getExternalStorageDirectory());
    }

    private fun getChosenFile(fileChosen: String): File {
        return if (fileChosen.equals("..")) {
            currentPath.parentFile
        } else {
            File(currentPath, fileChosen)
        }
    }

    private fun refresh(path: File) {
        currentPath = path
        if (path.exists()) {
            val dirs = path.listFiles { file -> file.isDirectory && file.canRead() }
            val files = path.listFiles { file ->
                if (!file.isDirectory) {
                    if (!file.canRead()) {
                        false
                    } else if (extension == null) {
                        true
                    } else {
                        file.name.toLowerCase(Locale.getDefault())
                            .endsWith(extension)
                    }
                } else {
                    false
                }
            }
            if (files == null) {
                dismiss()
                return
            }

            // convert to an array
            var i = 0
            val fileList: Array<String?>
            if (path.parentFile == null) {
                fileList = arrayOfNulls(dirs.size + files.size)
            } else {
                fileList = arrayOfNulls(dirs.size + files.size + 1)
                fileList[i++] = PARENT_DIR
            }
            Arrays.sort(dirs)
            Arrays.sort(files)
            for (dir in dirs) {
                fileList[i++] = dir.name
            }
            for (file in files) {
                fileList[i++] = file.name
            }

            // refresh the user interface
            setTitle(currentPath.path)
            list.adapter = object :
                ArrayAdapter<Any?>(context, android.R.layout.simple_list_item_1, fileList) {
                override fun getView(
                    pos: Int,
                    view: View?,
                    parent: ViewGroup
                ): View {
                    var view = view
                    view = super.getView(pos, view, parent)
                    (view as TextView).isSingleLine = true
                    return view
                }
            }
        }
    }

}