package com.example.conf.view.ui.fragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.DialogFragment.STYLE_NORMAL
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.conf.R
import kotlinx.android.synthetic.main.fragment_speakers_detail_dialog.*
import kotlinx.android.synthetic.main.item_speaker.*
import model.Speaker

class SpeakersDetailDialogFragment : DialogFragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.FullScreenDialogStyle)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_speakers_detail_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbarSpeaker.navigationIcon = ContextCompat.getDrawable(view.context, R.drawable.ic_close)
        toolbarSpeaker.setTitleTextColor(Color.WHITE)
        toolbarSpeaker.setNavigationOnClickListener{
            dismiss()
        }
        val speaker = arguments?.getSerializable("speaker") as Speaker
        toolbarSpeaker.title = speaker.name

        tvDetailSpeakerName.text = speaker.name
        tvDetailSpeakerJobtitle.text = speaker.jobtitle
        tvDetailSpeakerWorkplace.text = speaker.workplace
        tvDetailSpeakerBiography.text = speaker.biography
        Glide.with(ivDetailSpeakerImage.context)
                .load(speaker.image)
                .apply(RequestOptions.circleCropTransform())
                .into(ivDetailSpeakerImage)

    }
    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
    }
}