package my.game.loto.gameAction.screens

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import my.game.loto.R

class ResultFragment : Fragment(), View.OnClickListener {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle): View? {
        val view = inflater.inflate(R.layout.result_fragment, null)

        return view
    }

    override fun onClick(v: View?) {
        val nextFragment = activity as NextFragment
        if (v!!.id == R.id.button_front) {
            nextFragment.toFrontFragment()
        } else {
            nextFragment.toChoiceFragment()
        }
    }

    interface NextFragment{
        fun toFrontFragment()
        fun toChoiceFragment()
    }

}