package fr.upsilon.spotify.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import fr.upsilon.spotify.R


class RankFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_rank, container, false)
        val tabs = view.findViewById<TabLayout>(R.id.tabs)
        changeFragment(TrackRankFragment())
        tabs.addOnTabSelectedListener(object:TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab : TabLayout.Tab) {
                when (tab.position) {
                    0 -> changeFragment(TrackRankFragment())
                    1 -> changeFragment(AlbumRankFragment())
                }
            }
            override fun onTabUnselected(p0: TabLayout.Tab?) {

            }
            override fun onTabReselected(p0: TabLayout.Tab?) {

            }
        })
        return view
    }

    fun changeFragment(view: Fragment) {
        childFragmentManager.beginTransaction()
            .replace(R.id.rank_view, view)
            .commit()
    }

}
