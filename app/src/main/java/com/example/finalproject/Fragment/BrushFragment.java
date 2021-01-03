package com.example.finalproject.Fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.ToggleButton;

import com.example.finalproject.Interface.BrushInterface;
import com.example.finalproject.Adapter.ColorAdapter;
import com.example.finalproject.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.List;


public class BrushFragment extends BottomSheetDialogFragment implements ColorAdapter.ColorAdpaterListener {

    SeekBar seekBar_brush_size, seekBar_opacity;
    RecyclerView recycler_color;
    ToggleButton btn_brush_state;
    ColorAdapter colorAdapter;
    BrushInterface listener;
    static BrushFragment instance;
    public static BrushFragment getInstance(){
        if(instance==null)
        {
            instance = new BrushFragment();
        }
        return instance;
    }
    public void setListener(BrushInterface listener){
        this.listener=listener;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View itemView = inflater.inflate(R.layout.fragment_brush, container, false);

        seekBar_brush_size = (SeekBar) itemView.findViewById(R.id.seekbar_brush_size);
        seekBar_opacity = (SeekBar) itemView.findViewById(R.id.seekbar_opacity);
        btn_brush_state = (ToggleButton) itemView.findViewById(R.id.brush_state);
        recycler_color = (RecyclerView) itemView.findViewById(R.id.recycleview);
        recycler_color.setHasFixedSize(true);
        recycler_color.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,false));

        colorAdapter = new ColorAdapter(getContext(), genColorList(), this);
        recycler_color.setAdapter(colorAdapter);

        //
        seekBar_opacity.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                listener.onBrushOpacityListener(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        seekBar_brush_size.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                listener.onBrushSizeChangeListener(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        btn_brush_state.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                listener.onBrushStateChangedListener(isChecked);
            }
        });
        return itemView;
    }

    private List<Integer> genColorList() {
        List<Integer> colorList = new ArrayList<>();

        colorList.add(Color.parseColor("#131722"));
        colorList.add(Color.parseColor("#33ff33"));
        colorList.add(Color.parseColor("#ff0000"));
        colorList.add(Color.parseColor("#e600ac"));

        return colorList;
    }

    @Override
    public void colorSelected(int color) {
        listener.onBrushColorChangedListener(color);
    }
}