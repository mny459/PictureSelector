package com.luck.pictureselector.imagepicker

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.pm.ActivityInfo
import androidx.fragment.app.Fragment
import com.luck.picture.lib.PictureSelector
import com.luck.picture.lib.config.PictureConfig
import com.luck.picture.lib.config.PictureMimeType

/**
 *@author mny on 2020/3/12.
 *        Email：mny9@outlook.com
 *        Desc:
 */
object ImagePicker {
    /**
     * [pickVideo]
     */
    @SuppressLint("SourceLockedOrientationActivity")
    fun pick(context: Context, pickVideo: Boolean = false, maxNum: Int = 9, enableEdit: Boolean = false,
             enableCrop: Boolean = false, enableTrans: Boolean = false) {
        // 进入相册 以下是例子：不需要的api可以不写
        var selector: PictureSelector? = null
        if (context is Fragment) selector = PictureSelector.create(context)
        else if (context is Activity) selector = PictureSelector.create(context)
        if (selector == null) return
        val mimeType = if (pickVideo) PictureMimeType.ofAll() else PictureMimeType.ofImage()
        selector.openGallery(PictureMimeType.ofAll()) // 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                .loadImageEngine(GlideEngine.createGlideEngine()) // 外部传入图片加载引擎，必传项
                .isWeChatStyle(true)
//                .theme(themeId) // 主题样式设置 具体参考 values/styles   用法：R.style.picture.white.style v2.3.3后 建议使用setPictureStyle()动态方式
                .isWeChatStyle(true) // 是否开启微信图片选择风格
//                .isUseCustomCamera(cb_custom_camera.isChecked()) // 是否使用自定义相机
//                .setLanguage(language) // 设置语言，默认中文
                .setPictureStyle(PickerConfig.albumWeChatStyle()) // 动态自定义相册主题
//                .setPictureCropStyle(PickerConfig.cropStyle()) // 动态自定义裁剪主题
//                .setPictureWindowAnimationStyle(mWindowAnimationStyle) // 自定义相册启动退出动画
                .isWithVideoImage(true) // 图片和视频是否可以同选,只在ofAll模式下有效
////.setButtonFeatures(CustomCameraView.BUTTON_STATE_BOTH)// 设置自定义相机按钮状态
                .maxSelectNum(maxNum) // 最大图片选择数量
                .minSelectNum(0) // 最小选择数量
//                .maxVideoSelectNum(1) // 视频最大选择数量，如果没有单独设置的需求则可以不设置，同用maxSelectNum字段
////.minVideoSelectNum(1)// 视频最小选择数量，如果没有单独设置的需求则可以不设置，同用minSelectNum字段
                .imageSpanCount(4) // 每行显示个数
                .isReturnEmpty(true) // 未选择数据时点击按钮是否可以返回
                .isAndroidQTransform(true)// 是否需要处理Android Q 拷贝至应用沙盒的操作，只针对compress(false); && enableCrop(false);有效,默认处理
                .setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) // 设置相册Activity方向，不设置默认使用系统
                .isOriginalImageControl(true) // 是否显示原图控制按钮，如果设置为true则用户可以自由选择是否使用原图，压缩、裁剪功能将会失效
////.bindCustomPlayVideoCallback(callback)// 自定义视频播放回调控制，用户可以使用自己的视频播放界面
////.cameraFileName(System.currentTimeMillis() +".jpg")    // 重命名拍照文件名、如果是相册拍照则内部会自动拼上当前时间戳防止重复，注意这个只在使用相机时可以使用，如果使用相机又开启了压缩或裁剪 需要配合压缩和裁剪文件名api
////.renameCompressFile(System.currentTimeMillis() +".jpg")// 重命名压缩文件名、 注意这个不要重复，只适用于单张图压缩使用
////.renameCropFileName(System.currentTimeMillis() + ".jpg")// 重命名裁剪文件名、 注意这个不要重复，只适用于单张图裁剪使用
//                .selectionMode(if (cb_choose_mode.isChecked()) PictureConfig.MULTIPLE else PictureConfig.SINGLE) // 多选 or 单选
//                .isSingleDirectReturn(cb_single_back.isChecked()) // 单选模式下是否直接返回，PictureConfig.SINGLE模式下有效
                .previewImage(true) // 是否可预览图片
                .previewVideo(true) // 是否可预览视频
////.querySpecifiedFormatSuffix(PictureMimeType.ofJPEG())// 查询指定后缀格式资源
//                .enablePreviewAudio(cb_preview_audio.isChecked()) // 是否可播放音频
                .isCamera(false) // 是否显示拍照按钮
////.isMultipleSkipCrop(false)// 多图裁剪时是否支持跳过，默认支持
////.isMultipleRecyclerAnimation(false)// 多图裁剪底部列表显示动画效果
//                .isZoomAnim(true) // 图片列表点击 缩放效果 默认true
////.imageFormat(PictureMimeType.PNG)// 拍照保存图片格式后缀,默认jpeg
                .enableCrop(enableCrop) // 是否裁剪
                .enableEdit(enableEdit) // 是否编辑
                .enableTrans(enableTrans) // 是否转换数据结构我自己的数据结构
                .enableTrans(false)
////.basicUCropConfig()//对外提供所有UCropOptions参数配制，但如果PictureSelector原本支持设置的还是会使用原有的设置
//                .compress(cb_compress.isChecked()) // 是否压缩
                .minimumCompressSize(100) // 100 kb 以下的不压缩
////.compressQuality(80)// 图片压缩后输出质量 0~ 100
//                .synOrAsy(true) //同步true或异步false 压缩 默认同步
                .queryMaxFileSize(50)// 只查多少M以内的图片、视频、音频  单位M
////.compressSavePath(getPath())//压缩图片保存地址
////.sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效 注：已废弃
////.glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度 注：已废弃
//                .withAspectRatio(aspect_ratio_x, aspect_ratio_y) // 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
//                .hideBottomControls(if (cb_hide.isChecked()) false else true) // 是否显示uCrop工具栏，默认不显示
//                .isGif(cb_isGif.isChecked()) // 是否显示gif图片
//                .freeStyleCropEnabled(cb_styleCrop.isChecked()) // 裁剪框是否可拖拽
//                .circleDimmedLayer(cb_crop_circular.isChecked()) // 是否圆形裁剪
////.setCircleDimmedColor(ContextCompat.getColor(getContext(), R.color.app_color_white))// 设置圆形裁剪背景色值
////.setCircleDimmedBorderColor(ContextCompat.getColor(getApplicationContext(), R.color.app_color_white))// 设置圆形裁剪边框色值
////.setCircleStrokeWidth(3)// 设置圆形裁剪边框粗细
//                .showCropFrame(cb_showCropFrame.isChecked()) // 是否显示裁剪矩形边框 圆形裁剪时建议设为false
//                .showCropGrid(cb_showCropGrid.isChecked()) // 是否显示裁剪矩形网格 圆形裁剪时建议设为false
//                .openClickSound(cb_voice.isChecked()) // 是否开启点击声音
//                .selectionMedia(mAdapter.getData()) // 是否传入已选图片
////.isDragFrame(false)// 是否可拖动裁剪框(固定)
////.videoMinSecond(10)
                .videoMaxSecond(300)
////.recordVideoSecond(10)//录制视频秒数 默认60s
////.previewEggs(false)// 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中)
////.cropCompressQuality(90)// 注：已废弃 改用cutOutQuality()
//                .cutOutQuality(90) // 裁剪输出质量 默认100
//                .minimumCompressSize(100) // 小于100kb的图片不压缩
////.cropWH()// 裁剪宽高比，设置如果大于图片本身宽高则无效
////.cropImageWideHigh()// 裁剪宽高比，设置如果大于图片本身宽高则无效
////.rotateEnabled(false) // 裁剪是否可旋转图片
////.scaleEnabled(false)// 裁剪是否可放大缩小图片
////.videoQuality()// 视频录制质量 0 or 1
////.videoSecond()//显示多少秒以内的视频or音频也可适用
                .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
//                .forResult(object : OnResultCallbackListener {
//                    override fun onResult(result: List<LocalMedia>) {
//                        for (media in result) {
//                            Log.i(MainActivity.TAG, "是否压缩:" + media.isCompressed)
//                            Log.i(MainActivity.TAG, "压缩:" + media.compressPath)
//                            Log.i(MainActivity.TAG, "原图:" + media.path)
//                            Log.i(MainActivity.TAG, "是否裁剪:" + media.isCut)
//                            Log.i(MainActivity.TAG, "裁剪:" + media.cutPath)
//                            Log.i(MainActivity.TAG, "是否开启原图:" + media.isOriginal)
//                            Log.i(MainActivity.TAG, "原图路径:" + media.originalPath)
//                            Log.i(MainActivity.TAG, "图片编辑图片:" + media.editPath)
//                            Log.i(MainActivity.TAG, "Android Q 特有Path:" + media.androidQToPath)
//                        }
//                        mAdapter.setList(result)
//                        mAdapter.notifyDataSetChanged()
//                    }
//
//                    override fun onCancel() {
//                        Log.i(MainActivity.TAG, "PictureSelector Cancel")
//                    }
//                })
    }
}