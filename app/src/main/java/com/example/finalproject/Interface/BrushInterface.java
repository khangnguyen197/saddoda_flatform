package com.example.finalproject.Interface;

public interface BrushInterface {
    void onBrushSizeChangeListener(float size);
    void onBrushOpacityListener(int opacity);
    void onBrushColorChangedListener(int color);
    void onBrushStateChangedListener(boolean isEraser);
}

