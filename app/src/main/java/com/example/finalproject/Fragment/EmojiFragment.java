package com.example.finalproject.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.Adapter.EmojiAdapter;
import com.example.finalproject.Interface.EmojiInterface;
import com.example.finalproject.Interface.EmojiInterface;
import com.example.finalproject.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import ja.burhanrashid52.photoeditor.PhotoEditor;


public class EmojiFragment extends BottomSheetDialogFragment implements EmojiAdapter.EmojiAdapterListener {
    RecyclerView recycler_emoji;
    static com.example.finalproject.Fragment.EmojiFragment instance;



    EmojiInterface listener;

    public void setListener(EmojiInterface listener) {
        this.listener = listener;
    }

    public static com.example.finalproject.Fragment.EmojiFragment getInstance() {
        if(instance == null)
            instance = new com.example.finalproject.Fragment.EmojiFragment();
        return instance;
    }

    public EmojiFragment(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View itemView= inflater.inflate(R.layout.fragment_emoji, container, false);
        recycler_emoji = (RecyclerView) itemView.findViewById(R.id.recycler_emoji);
        recycler_emoji.setHasFixedSize(true);
        recycler_emoji.setLayoutManager(new GridLayoutManager(getActivity(),5));

        EmojiAdapter adapter = new EmojiAdapter(getContext(), PhotoEditor.getEmojis(getContext()),this);
        recycler_emoji.setAdapter(adapter);

        return itemView;
    }

    @Override
    public void onEmojiItemSelected(String emoji) {
        listener.onEmojiSelected(emoji);
    }
}