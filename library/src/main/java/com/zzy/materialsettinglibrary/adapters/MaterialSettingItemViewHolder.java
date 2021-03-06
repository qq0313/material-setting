package com.zzy.materialsettinglibrary.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;

import com.zzy.materialsettinglibrary.R;
import com.zzy.materialsettinglibrary.model.MaterialSettingActionItem;
import com.zzy.materialsettinglibrary.model.MaterialSettingCompoundButtonItem;
import com.zzy.materialsettinglibrary.model.MaterialSettingItem;
import com.zzy.materialsettinglibrary.utils.MaterialSettingSharedPreferences;

/**
 * Created by zzyandzzy on 217/1/16.
 */

public class MaterialSettingItemViewHolder
        extends RecyclerView.ViewHolder implements View.OnClickListener{
    public View view;
    public ImageView icon;
    public CompoundButton compoundButton;
    public CheckBox checkBox;
    public Switch aSwitch;
    private LinearLayout linearLayout;
    public RadioButton radioButton;
    public TextView text;
    public TextView subText;
    public int viewType;
    public int ButtonPosition;
    public MaterialSettingActionItem.OnClickListener onClickListener;
    public MaterialSettingCompoundButtonItem.OnCheckedChangeListener onCheckedChangeListener;
    private Context context;
    private MaterialSettingSharedPreferences settingSharedPreferences;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public MaterialSettingItemViewHolder(View itemView, int viewType, int ButtonPosition) {
        super(itemView);
        this.view = itemView;
        this.viewType = viewType;
        this.ButtonPosition = ButtonPosition;
        this.context = view.getContext();
        this.settingSharedPreferences = new MaterialSettingSharedPreferences(context);
        text = (TextView) view.findViewById(R.id.mal_text_item_text);
        subText = (TextView) view.findViewById(R.id.mal_text_item_subText);
        switch (viewType){
            case MaterialSettingItem.ItemType.ACTION_ITEM:
                icon = (ImageView) view.findViewById(R.id.mal_action_item_icon);
                view.setOnClickListener(this);
                this.onClickListener = null;
                break;
            case MaterialSettingItem.ItemType.TITLE_ITEM:
                icon = (ImageView) view.findViewById(R.id.mal_title_item_icon);
                text = (TextView) view.findViewById(R.id.mal_title_item_text);
                break;
            case MaterialSettingItem.ItemType.CHECKBOX_ITEM:
                if (ButtonPosition != MaterialSettingItem.ButtonPosition.NULL
                        && ButtonPosition == MaterialSettingItem.ButtonPosition.LEFT){
                    checkBox = (CheckBox) view.findViewById(R.id.mal_checkbox_item_checkbox);
                    checkBox.setVisibility(View.GONE);
                    checkBox = null;
                    checkBox = (CheckBox) view.findViewById(R.id.mal_checkbox_item_checkboxleft);
                }else {
                    checkBox = (CheckBox) view.findViewById(R.id.mal_checkbox_item_checkboxleft);
                    checkBox.setVisibility(View.GONE);
                    checkBox = null;
                    checkBox = (CheckBox) view.findViewById(R.id.mal_checkbox_item_checkbox);
                }
                compoundButton = checkBox;
                break;
            case MaterialSettingItem.ItemType.SWITCH_ITEM:
                linearLayout = (LinearLayout) view.findViewById(R.id.mal_material_setting_text_linear);
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) linearLayout.getLayoutParams();
                if (ButtonPosition != MaterialSettingItem.ButtonPosition.NULL
                        && ButtonPosition == MaterialSettingItem.ButtonPosition.LEFT){
                    aSwitch = (Switch) view.findViewById(R.id.mal_switch_item_switch);
                    aSwitch.setVisibility(View.GONE);
                    aSwitch = null;
                    aSwitch = (Switch) view.findViewById(R.id.mal_switch_item_switchleft);
                    int px = dip2px(context,1);
                    layoutParams.setMarginStart(px);
                    layoutParams.setMargins(px, dip2px(context,8),
                            layoutParams.rightMargin,layoutParams.getMarginEnd());
                }else {
                    aSwitch = (Switch) view.findViewById(R.id.mal_switch_item_switchleft);
                    aSwitch.setVisibility(View.GONE);
                    aSwitch = null;
                    aSwitch = (Switch) view.findViewById(R.id.mal_switch_item_switch);
                }
                linearLayout.setLayoutParams(layoutParams);
                compoundButton = aSwitch;
                break;
            case MaterialSettingItem.ItemType.RADIOBUTTON_ITEM:
                if (ButtonPosition != MaterialSettingItem.ButtonPosition.NULL
                        && ButtonPosition == MaterialSettingItem.ButtonPosition.LEFT){
                    radioButton = (RadioButton) view.findViewById(R.id.mal_radiobutton_item_radiobutton);
                    radioButton.setVisibility(View.GONE);
                    radioButton = null;
                    radioButton = (RadioButton) view.findViewById(R.id.mal_radiobutton_item_radiobuttonleft);
                }else {
                    radioButton = (RadioButton) view.findViewById(R.id.mal_radiobutton_item_radiobuttonleft);
                    radioButton.setVisibility(View.GONE);
                    radioButton = null;
                    radioButton = (RadioButton) view.findViewById(R.id.mal_radiobutton_item_radiobutton);
                }
                compoundButton = radioButton;
                break;
        }
    }

    @Override
    public void onClick(View v) {
        Log.d("TAG","onClick:");
        if (onClickListener != null)
            onClickListener.onClick();
    }

    public int dip2px(Context context,float dip){
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int)(dip * scale + 0.5f);
    }

    public void setButtonText(MaterialSettingItemViewHolder holder,
                              MaterialSettingCompoundButtonItem item,boolean isChecked){
        if (isChecked){
            CharSequence changeOnText = item.getChangeOnText();
            CharSequence changeOnSubText = item.getChangeOnSubText();
            int changeOnTextRes = item.getChangeOnTextRes();
            int changeOnSubTextRes = item.getChangeOnSubTextRes();
            if (changeOnText != null)
                holder.text.setText(changeOnText);
            else if (changeOnTextRes != 0)
                holder.text.setText(changeOnTextRes);
            if (changeOnSubText != null)
                holder.subText.setText(changeOnSubText);
            else if (changeOnSubTextRes != 0)
                holder.subText.setText(changeOnSubTextRes);
        }else {
            CharSequence changeOffText = item.getChangeOffText();
            CharSequence changeOffSubText = item.getChangeOffSubText();
            int changeOffTextRes = item.getChangeOffTextRes();
            int changeOffSubTextRes = item.getChangeOffSubTextRes();
            if (changeOffText != null)
                holder.text.setText(changeOffText);
            else if (changeOffTextRes != 0)
                holder.text.setText(changeOffTextRes);
            if (changeOffSubText != null)
                holder.subText.setText(changeOffSubText);
            else if (changeOffSubTextRes != 0)
                holder.subText.setText(changeOffSubTextRes);
        }
    }

    public void setText(TextView text, TextView subText, ImageView icon,
                         CharSequence textStr, int textRes, CharSequence subTextStr, int subTextRes,
                         Drawable drawable, int drawableRes) {
        if (text != null){
            text.setVisibility(View.VISIBLE);
            if (textStr != null)
                text.setText(textStr);
            else if (textRes != 0)
                text.setText(textRes);
            else
                text.setVisibility(View.GONE);
        }
        if (subText != null){
            subText.setVisibility(View.VISIBLE);
            if (subTextStr != null)
                subText.setText(subTextStr);
            else if (subTextRes != 0)
                subText.setText(subTextRes);
            else
                subText.setVisibility(View.GONE);
        }
        if (icon != null){
            icon.setVisibility(View.VISIBLE);
            if (drawable != null)
                icon.setImageDrawable(drawable);
            else if (drawableRes != 0)
                icon.setImageResource(drawableRes);
            else
                icon.setVisibility(View.GONE);
        }
    }

    public void setSettingSharedPreferences(
            MaterialSettingCompoundButtonItem item,boolean isChecked){
        String key = item.getKey();
        if (key == null)
            key = context.getString(R.string.app_name);
        settingSharedPreferences.putBoolean(key,isChecked);
        Log.d("TAG","putBoolean:" + isChecked);
    }

}
