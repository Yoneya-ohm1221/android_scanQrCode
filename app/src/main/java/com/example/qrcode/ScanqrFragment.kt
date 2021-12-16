package com.example.qrcode
import android.Manifest
import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
open class ScanqrFragment : Fragment() {
    var textView: TextView? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
// Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_scanqr, container, false)
        textView = root.findViewById(R.id.textView)
//Set permission to open camera and access a directory
        if (ContextCompat.checkSelfPermission(requireContext() , Manifest.permission.CAMERA
            )!= PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissions(arrayOf(Manifest.permission.CAMERA),1)
        } else {
            try {
                val intent = Intent("com.google.zxing.client.android.SCAN")
                intent.putExtra("SCAN_MODE", "QR_CODE_MODE") //PRODUCT_MODE for bar code
                startActivityForResult(intent, 1)
            } catch (e: Exception) {
                val marketUri =
                    Uri.parse("market://details?id=com.google.zxing.client.android")
                val marketIntent = Intent(Intent.ACTION_VIEW, marketUri)
                startActivity(marketIntent)
            }
        }
        return root
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 1 && resultCode == RESULT_OK) {
            textView?.text = data?.getStringExtra("SCAN_RESULT")
        } else if (resultCode == Activity.RESULT_CANCELED) {
// User Cancelled the action
        }
    }
}