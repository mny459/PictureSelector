package com.luck.pictureselector.imagepicker

import android.graphics.Color
import com.luck.picture.lib.config.UCropOptions
import com.luck.picture.lib.style.PictureCropParameterStyle
import com.luck.picture.lib.style.PictureParameterStyle
import com.luck.picture.lib.style.PictureWindowAnimationStyle
import com.luck.pictureselector.R


/**
 *@author mny on 2020/2/26.
 *        Email：mny9@outlook.com
 *        Desc:
 */
object PickerConfig {

    fun albumWeChatStyle(): PictureParameterStyle {
        // 相册主题
        val black = Color.parseColor("#000000")
        var mPictureParameterStyle = PictureParameterStyle()
        // 是否改变状态栏字体颜色(黑白切换)
        // 是否改变状态栏字体颜色(黑白切换)
        mPictureParameterStyle.isChangeStatusBarFontColor = false
        // 是否开启右下角已完成(0/9)风格
        // 是否开启右下角已完成(0/9)风格
        mPictureParameterStyle.isOpenCompletedNumStyle = false
        // 是否开启类似QQ相册带数字选择风格
        // 是否开启类似QQ相册带数字选择风格
        mPictureParameterStyle.isOpenCheckNumStyle = true
        // 状态栏背景色
        // 状态栏背景色
        mPictureParameterStyle.pictureStatusBarColor = Color.parseColor("#393a3e")
        // 相册列表标题栏背景色
        // 相册列表标题栏背景色
        mPictureParameterStyle.pictureTitleBarBackgroundColor = Color.parseColor("#393a3e")
        // 相册父容器背景色
        // 相册父容器背景色
        mPictureParameterStyle.pictureContainerBackgroundColor = black
        // 相册列表标题栏右侧上拉箭头
        // 相册列表标题栏右侧上拉箭头
        mPictureParameterStyle.pictureTitleUpResId = R.drawable.picture_icon_wechat_up
        // 相册列表标题栏右侧下拉箭头
        // 相册列表标题栏右侧下拉箭头
        mPictureParameterStyle.pictureTitleDownResId = R.drawable.picture_icon_wechat_down
        // 相册文件夹列表选中圆点
        // 相册文件夹列表选中圆点
        mPictureParameterStyle.pictureFolderCheckedDotStyle = R.drawable.picture_orange_oval
        // 相册返回箭头
        // 相册返回箭头
        mPictureParameterStyle.pictureLeftBackIcon = R.drawable.picture_icon_close
        // 标题栏字体颜色
        // 标题栏字体颜色
        mPictureParameterStyle.pictureTitleTextColor = Color.parseColor("#FFFFFF")
        // 相册右侧按钮字体颜色  废弃 改用.pictureRightDefaultTextColor和.pictureRightDefaultTextColor
        // 相册右侧按钮字体颜色  废弃 改用.pictureRightDefaultTextColor和.pictureRightDefaultTextColor
        mPictureParameterStyle.pictureCancelTextColor = Color.parseColor("#53575e")
        // 相册右侧按钮字体默认颜色
        // 相册右侧按钮字体默认颜色
        mPictureParameterStyle.pictureRightDefaultTextColor = Color.parseColor("#53575e")
        // 相册右侧按可点击字体颜色,只针对isWeChatStyle 为true时有效果
        // 相册右侧按可点击字体颜色,只针对isWeChatStyle 为true时有效果
        mPictureParameterStyle.pictureRightSelectedTextColor = Color.parseColor("#FFFFFF")
        // 相册右侧按钮背景样式,只针对isWeChatStyle 为true时有效果
        // 相册右侧按钮背景样式,只针对isWeChatStyle 为true时有效果
        mPictureParameterStyle.pictureUnCompleteBackgroundStyle = R.drawable.picture_send_button_default_bg
        // 相册右侧按钮可点击背景样式,只针对isWeChatStyle 为true时有效果
        // 相册右侧按钮可点击背景样式,只针对isWeChatStyle 为true时有效果
        mPictureParameterStyle.pictureCompleteBackgroundStyle = R.drawable.picture_send_button_bg
        // 相册列表勾选图片样式
        // 相册列表勾选图片样式
        mPictureParameterStyle.pictureCheckedStyle = R.drawable.picture_wechat_num_selector
        // 相册标题背景样式 ,只针对isWeChatStyle 为true时有效果
        // 相册标题背景样式 ,只针对isWeChatStyle 为true时有效果
        mPictureParameterStyle.pictureWeChatTitleBackgroundStyle = R.drawable.picture_album_bg
        // 微信样式 预览右下角样式 ,只针对isWeChatStyle 为true时有效果
        // 微信样式 预览右下角样式 ,只针对isWeChatStyle 为true时有效果
        mPictureParameterStyle.pictureWeChatChooseStyle = R.drawable.picture_wechat_select_cb
        // 相册返回箭头 ,只针对isWeChatStyle 为true时有效果
        // 相册返回箭头 ,只针对isWeChatStyle 为true时有效果
        mPictureParameterStyle.pictureWeChatLeftBackStyle = R.drawable.picture_icon_back
        // 相册列表底部背景色
        // 相册列表底部背景色
        mPictureParameterStyle.pictureBottomBgColor = Color.parseColor("#393a3e")
        // 已选数量圆点背景样式
        // 已选数量圆点背景样式
        mPictureParameterStyle.pictureCheckNumBgStyle = R.drawable.picture_num_oval
        // 相册列表底下预览文字色值(预览按钮可点击时的色值)
        // 相册列表底下预览文字色值(预览按钮可点击时的色值)
        mPictureParameterStyle.picturePreviewTextColor = Color.parseColor("#FFFFFF")
        // 相册列表底下不可预览文字色值(预览按钮不可点击时的色值)
        // 相册列表底下不可预览文字色值(预览按钮不可点击时的色值)
        mPictureParameterStyle.pictureUnPreviewTextColor = Color.parseColor("#9b9b9b")
        // 相册列表已完成色值(已完成 可点击色值)
        // 相册列表已完成色值(已完成 可点击色值)
        mPictureParameterStyle.pictureCompleteTextColor = Color.parseColor("#FFFFFF")
        // 相册列表未完成色值(请选择 不可点击色值)
        // 相册列表未完成色值(请选择 不可点击色值)
        mPictureParameterStyle.pictureUnCompleteTextColor = Color.parseColor("#53575e")
        // 预览界面底部背景色
        // 预览界面底部背景色
        mPictureParameterStyle.picturePreviewBottomBgColor = Color.parseColor("#9b9b9b")
        // 外部预览界面删除按钮样式
        // 外部预览界面删除按钮样式
        mPictureParameterStyle.pictureExternalPreviewDeleteStyle = R.drawable.picture_icon_delete
        // 原图按钮勾选样式  需设置.isOriginalImageControl(true); 才有效
        // 原图按钮勾选样式  需设置.isOriginalImageControl(true); 才有效
        mPictureParameterStyle.pictureOriginalControlStyle = R.drawable.picture_original_wechat_checkbox
        // 原图文字颜色 需设置.isOriginalImageControl(true); 才有效
        // 原图文字颜色 需设置.isOriginalImageControl(true); 才有效
        mPictureParameterStyle.pictureOriginalFontColor = Color.parseColor("#FFFFFF")
        // 外部预览界面是否显示删除按钮
        // 外部预览界面是否显示删除按钮
        mPictureParameterStyle.pictureExternalPreviewGonePreviewDelete = true
        // 设置NavBar Color SDK Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP有效
        // 设置NavBar Color SDK Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP有效
        mPictureParameterStyle.pictureNavBarColor = Color.parseColor("#393a3e")

        // 完成文案是否采用(%1$d/%2$d)的字符串，只允许两个占位符哟
//        mPictureParameterStyle.isCompleteReplaceNum = true;
        // 自定义相册右侧文本内容设置
//        mPictureParameterStyle.pictureUnCompleteText = getString(R.string.app_wechat_send);
        //自定义相册右侧已选中时文案 支持占位符String 但只支持两个 必须isCompleteReplaceNum为true
//        mPictureParameterStyle.pictureCompleteText = getString(R.string.app_wechat_send_num);
//        // 自定义相册列表不可预览文字
//        mPictureParameterStyle.pictureUnPreviewText = "";
//        // 自定义相册列表预览文字
//        mPictureParameterStyle.picturePreviewText = "";
//        // 自定义预览页右下角选择文字文案
//        mPictureParameterStyle.pictureWeChatPreviewSelectedText = "";

//        // 自定义相册标题文字大小
//        mPictureParameterStyle.pictureTitleTextSize = 9;
//        // 自定义相册右侧文字大小
//        mPictureParameterStyle.pictureRightTextSize = 9;
//        // 自定义相册预览文字大小
//        mPictureParameterStyle.picturePreviewTextSize = 9;
//        // 自定义相册完成文字大小
//        mPictureParameterStyle.pictureCompleteTextSize = 9;
//        // 自定义原图文字大小
//        mPictureParameterStyle.pictureOriginalTextSize = 9;
//        // 自定义预览页右下角选择文字大小
//        mPictureParameterStyle.pictureWeChatPreviewSelectedTextSize = 9;

        // 裁剪主题
        // 完成文案是否采用(%1$d/%2$d)的字符串，只允许两个占位符哟
//        mPictureParameterStyle.isCompleteReplaceNum = true;
// 自定义相册右侧文本内容设置
//        mPictureParameterStyle.pictureUnCompleteText = getString(R.string.app_wechat_send);
//自定义相册右侧已选中时文案 支持占位符String 但只支持两个 必须isCompleteReplaceNum为true
//        mPictureParameterStyle.pictureCompleteText = getString(R.string.app_wechat_send_num);
//        // 自定义相册列表不可预览文字
//        mPictureParameterStyle.pictureUnPreviewText = "";
//        // 自定义相册列表预览文字
//        mPictureParameterStyle.picturePreviewText = "";
//        // 自定义预览页右下角选择文字文案
//        mPictureParameterStyle.pictureWeChatPreviewSelectedText = "";
//        // 自定义相册标题文字大小
//        mPictureParameterStyle.pictureTitleTextSize = 9;
//        // 自定义相册右侧文字大小
//        mPictureParameterStyle.pictureRightTextSize = 9;
//        // 自定义相册预览文字大小
//        mPictureParameterStyle.picturePreviewTextSize = 9;
//        // 自定义相册完成文字大小
//        mPictureParameterStyle.pictureCompleteTextSize = 9;
//        // 自定义原图文字大小
//        mPictureParameterStyle.pictureOriginalTextSize = 9;
//        // 自定义预览页右下角选择文字大小
//        mPictureParameterStyle.pictureWeChatPreviewSelectedTextSize = 9;
// 裁剪主题
        return mPictureParameterStyle
    }

    fun cropStyle(pictureStyle: PictureParameterStyle): PictureCropParameterStyle {
        val mCropParameterStyle = PictureCropParameterStyle(
                Color.parseColor("#393a3e"),
                Color.parseColor("#393a3e"),
                Color.parseColor("#393a3e"),
                Color.parseColor("#FFFFFF"),
                pictureStyle.isChangeStatusBarFontColor)
        return mCropParameterStyle
    }

    fun windowAnimationStyle(): PictureWindowAnimationStyle {
        // 相册启动退出动画   
        val windowAnimationStyle = PictureWindowAnimationStyle();
        windowAnimationStyle.ofAllAnimation(R.anim.picture_anim_up_in, R.anim.picture_anim_down_out);
        return windowAnimationStyle
    }
    fun uCropConfig(): UCropOptions {
        // 相册启动退出动画
        val options = UCropOptions()
        options.setCircleDimmedLayer(true)
        return options
    }
}