package test.navigation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import test.navigation.R

abstract  class BaseSubFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.frag_sub,container,false);
        v.findViewById<TextView>(R.id.sub_text).text = getTitle()
        return v
    }

    abstract fun getTitle():String
}