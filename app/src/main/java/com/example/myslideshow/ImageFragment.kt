package com.example.myslideshow


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.autofill.AutofillId
import kotlinx.android.synthetic.main.fragment_image.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
val IMG_RES_ID = "IMG_RES_ID"

class ImageFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_image, container, false)
    }

    companion object {
        fun newInstance(imageResourceId: Int) : ImageFragment {
            val bundle = Bundle()
            bundle.putInt(IMG_RES_ID, imageResourceId)
            val imageFragment = ImageFragment()
            imageFragment.arguments = bundle
            return imageFragment
        }
    }

    private var imgResId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            imgResId = it.getInt(IMG_RES_ID)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        imgResId?.let {
            imageView.setImageResource(it)
        }
    }
}
