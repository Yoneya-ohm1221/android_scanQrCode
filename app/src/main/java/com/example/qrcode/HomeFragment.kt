package com.example.qrcode
import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.common.BitMatrix
class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
// Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_home, container, false)
//Generate QR Code
        var imageView: ImageView = root.findViewById(R.id.imageView)
        val bitmap = GenerateQRCode("1001")
        imageView.setImageBitmap(bitmap)
        return root
    }
    private fun GenerateQRCode(Value: String): Bitmap? {
        val bitMatrix: BitMatrix = MultiFormatWriter().encode(
            Value,
            BarcodeFormat.QR_CODE, 500, 500, null
        )
        val bitMatrixWidth = bitMatrix.width
        val bitMatrixHeight = bitMatrix.height
        val pixels = IntArray(bitMatrixWidth * bitMatrixHeight)
        for (y in 0 until bitMatrixHeight) {
            val offset = y * bitMatrixWidth
            for (x in 0 until bitMatrixWidth) {
                pixels[offset + x] = if (bitMatrix[x, y]
                ) resources.getColor(R.color.black) else resources.getColor(R.color.white)
            }
        }
        val bitmap =
            Bitmap.createBitmap(bitMatrixWidth, bitMatrixHeight, Bitmap.Config.ARGB_4444)
        bitmap.setPixels(pixels, 0, 500, 0, 0, bitMatrixWidth, bitMatrixHeight)
        return bitmap
    }
}