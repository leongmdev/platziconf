package com.example.conf.view.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.conf.R
import com.example.conf.view.adapter.SpeakerAdapter
import com.example.conf.view.adapter.SpeakerListener
import com.example.conf.viewmodel.SpeakersViewModel
import kotlinx.android.synthetic.main.fragment_speakers.*
import model.Speaker

class SpeakersFragment : Fragment(), SpeakerListener {
    private lateinit var speakerAdapter: SpeakerAdapter
    private lateinit var viewModel: SpeakersViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_speakers, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(SpeakersViewModel::class.java)
        viewModel.refresh()

        speakerAdapter = SpeakerAdapter(this)

        rvSpeakers.apply {
            layoutManager = GridLayoutManager(activity, 2)
            adapter = speakerAdapter
        }
        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.listSpeakers.observe(this, Observer<List<Speaker>> {speakers ->
            speakers.let {
                speakerAdapter.updateData(speakers)
            }
        })

        viewModel.isLoading.observe(this, Observer<Boolean> {
            if(it != null)
                rlBaseSpeakers.visibility = View.INVISIBLE
        })
    }
    override fun onSpeakerClicked(speaker: Speaker, position: Int) {
        var bundle = bundleOf("speaker" to speaker)
        findNavController().navigate(R.id.speakersDetailFragmentDialog, bundle)
       }
}