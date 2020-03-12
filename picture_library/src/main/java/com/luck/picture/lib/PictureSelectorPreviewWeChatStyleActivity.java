package com.luck.picture.lib;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.luck.picture.lib.adapter.PictureWeChatPreviewGalleryAdapter;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.decoration.GridSpacingItemDecoration;
import com.luck.picture.lib.decoration.WrapContentLinearLayoutManager;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.tools.ScreenUtils;

import java.io.File;
import java.util.UUID;

import me.kareluo.imaging.IMGEditActivity;
import me.kareluo.imaging.IMGGalleryActivity;


/**
 * @author：luck
 * @date：2019-11-30 17:16
 * @describe：PictureSelector 预览微信风格
 */
public class PictureSelectorPreviewWeChatStyleActivity extends PicturePreviewActivity {
    private static final String TAG = "PictureSelectorPreviewW";
    private static final int REQ_IMAGE_EDIT = 1111;
    private final static int ALPHA_DURATION = 300;
    private TextView mPictureSendView;
    private RecyclerView mRvGallery;
    private TextView tvSelected;
    private TextView tvEdit;
    private View bottomLine;
    private PictureWeChatPreviewGalleryAdapter mGalleryAdapter;
    private int mCurSelectedPosition = 0;

    @Override
    public int getResourceId() {
        return R.layout.picture_wechat_style_preview;
    }

    private void goneParent() {
        if (tv_img_num.getVisibility() == View.VISIBLE) {
            tv_img_num.setVisibility(View.GONE);
        }
        if (mTvPictureOk.getVisibility() == View.VISIBLE) {
            mTvPictureOk.setVisibility(View.GONE);
        }
        check.setText("");
    }

    @Override
    protected void initWidgets() {
        super.initWidgets();
        goneParent();
        mRvGallery = findViewById(R.id.rv_gallery);
        bottomLine = findViewById(R.id.bottomLine);
        tvSelected = findViewById(R.id.tv_selected);
        tvEdit = findViewById(R.id.tv_edit);
        tvEdit.setOnClickListener(this);
        mPictureSendView = findViewById(R.id.picture_send);
        mPictureSendView.setOnClickListener(this);
        mPictureSendView.setText(getString(R.string.picture_send));
        mCbOriginal.setTextSize(16);
        mGalleryAdapter = new PictureWeChatPreviewGalleryAdapter(config);
        WrapContentLinearLayoutManager layoutManager = new WrapContentLinearLayoutManager(getContext());
        layoutManager.setOrientation(WrapContentLinearLayoutManager.HORIZONTAL);
        mRvGallery.setLayoutManager(layoutManager);
        mRvGallery.addItemDecoration(new GridSpacingItemDecoration(Integer.MAX_VALUE,
                ScreenUtils.dip2px(this, 8), false));
        mRvGallery.setAdapter(mGalleryAdapter);
        mGalleryAdapter.setItemClickListener((position, media, v) -> {
            if (viewPager != null && media != null) {
                int newPosition = is_bottom_preview ? position : media.position - 1;
                viewPager.setCurrentItem(newPosition);
                mCurSelectedPosition = newPosition;
            }
        });
        if (is_bottom_preview) {
            if (selectImages != null && selectImages.size() > position) {
                selectImages.get(position).setChecked(true);
            }
        } else {
            int size = selectImages != null ? selectImages.size() : 0;
            for (int i = 0; i < size; i++) {
                LocalMedia media = selectImages.get(i);
                media.setChecked(media.position - 1 == position);
            }
        }
    }

    @Override
    public void initPictureSelectorStyle() {
        super.initPictureSelectorStyle();
        if (config.style != null) {
            if (config.style.pictureCompleteBackgroundStyle != 0) {
                mPictureSendView.setBackgroundResource(config.style.pictureCompleteBackgroundStyle);
            } else {
                mPictureSendView.setBackgroundResource(R.drawable.picture_send_button_bg);
            }
            if (config.style.pictureRightTextSize != 0) {
                mPictureSendView.setTextSize(config.style.pictureRightTextSize);
            }
            if (!TextUtils.isEmpty(config.style.pictureWeChatPreviewSelectedText)) {
                tvSelected.setText(config.style.pictureWeChatPreviewSelectedText);
            }
            if (config.style.pictureWeChatPreviewSelectedTextSize != 0) {
                tvSelected.setTextSize(config.style.pictureWeChatPreviewSelectedTextSize);
            }
            if (config.style.picturePreviewBottomBgColor != 0) {
                selectBarLayout.setBackgroundColor(config.style.picturePreviewBottomBgColor);
            } else {
                selectBarLayout.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.picture_color_half_grey));
            }
            if (config.style.pictureCompleteTextColor != 0) {
                mPictureSendView.setTextColor(config.style.pictureCompleteTextColor);
            } else {
                if (config.style.pictureCancelTextColor != 0) {
                    mPictureSendView.setTextColor(config.style.pictureCancelTextColor);
                } else {
                    mPictureSendView.setTextColor(ContextCompat.getColor(getContext(), R.color.picture_color_white));
                }
            }
            if (config.style.pictureOriginalFontColor == 0) {
                mCbOriginal.setTextColor(ContextCompat
                        .getColor(this, R.color.picture_color_white));
            }
            if (config.style.pictureWeChatChooseStyle != 0) {
                check.setBackgroundResource(config.style.pictureWeChatChooseStyle);
            } else {
                check.setBackgroundResource(R.drawable.picture_wechat_select_cb);
            }
            if (config.isOriginalControl) {
                if (config.style.pictureOriginalControlStyle == 0) {
                    mCbOriginal.setButtonDrawable(ContextCompat
                            .getDrawable(this, R.drawable.picture_original_wechat_checkbox));
                }
            }
            if (config.style.pictureWeChatLeftBackStyle != 0) {
                picture_left_back.setImageResource(config.style.pictureWeChatLeftBackStyle);
            } else {
                picture_left_back.setImageResource(R.drawable.picture_icon_back);
            }
            if (!TextUtils.isEmpty(config.style.pictureUnCompleteText)) {
                mPictureSendView.setText(config.style.pictureUnCompleteText);
            }
        } else {
            mPictureSendView.setBackgroundResource(R.drawable.picture_send_button_bg);
            mPictureSendView.setTextColor(ContextCompat.getColor(getContext(), R.color.picture_color_white));
            selectBarLayout.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.picture_color_half_grey));
            check.setBackgroundResource(R.drawable.picture_wechat_select_cb);
            picture_left_back.setImageResource(R.drawable.picture_icon_back);
            mCbOriginal.setTextColor(ContextCompat
                    .getColor(this, R.color.picture_color_white));
            if (config.isOriginalControl) {
                mCbOriginal.setButtonDrawable(ContextCompat
                        .getDrawable(this, R.drawable.picture_original_wechat_checkbox));
            }
        }

        onSelectNumChange(false);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        int id = v.getId();
        if (id == R.id.picture_send) {
            boolean enable = selectImages.size() != 0;
            if (enable) {
                // 如果已有勾选则走完成逻辑
                mTvPictureOk.performClick();
            } else {
                // 没有勾选走选中逻辑并完成
                btnCheck.performClick();
                boolean newEnableStatus = selectImages.size() != 0;
                if (newEnableStatus) {
                    // 如果已有勾选则走完成逻辑
                    mTvPictureOk.performClick();
                }
            }
        } else if (id == R.id.tv_edit) {
            try {
                LocalMedia media = images.get(mCurSelectedPosition);
                Log.d(TAG, "onClick: 传餐 = "+media);
                String originalPath = media.getPath();
                if (!TextUtils.isEmpty(media.getEditPath())) {
                    originalPath = media.getEditPath();
                }
                File file = new File(originalPath);
                File editImgFile = new File(this.getCacheDir(), UUID.randomUUID().toString() + ".png");
                media.setEditPath(editImgFile.getAbsolutePath());

                Intent intent = new Intent(this, IMGEditActivity.class);
                intent.putExtra(IMGEditActivity.EXTRA_IMAGE_URI, Uri.fromFile(file));
                intent.putExtra(IMGEditActivity.EXTRA_IMAGE_SAVE_PATH, editImgFile.getAbsolutePath());
                startActivityForResult(intent, REQ_IMAGE_EDIT);
            } catch (Exception e) {
                Log.e(TAG, "onClick: edit error = ", e);
            }
        }
    }

    @Override
    protected void onSelectedChange(boolean isAddRemove, LocalMedia media) {
        super.onSelectedChange(isAddRemove, media);
        if (isAddRemove) {
            // 添加
            media.setChecked(true);
            if (config.selectionMode == PictureConfig.SINGLE) {
                mGalleryAdapter.addSingleMediaToData(media);
            }
        } else {
            // 移除
            media.setChecked(false);
            mGalleryAdapter.removeMediaToData(media);
        }
    }

    @Override
    protected void onPageSelectedChange(LocalMedia media) {
        super.onPageSelectedChange(media);
        goneParent();
        if (mGalleryAdapter != null) {
            int itemCount = mGalleryAdapter.getItemCount();
            for (int i = 0; i < itemCount; i++) {
                LocalMedia item = mGalleryAdapter.getItem(i);
                if (item == null || TextUtils.isEmpty(item.getPath())) {
                    continue;
                }
                item.setChecked(item.getPath().equals(media.getPath())
                        || item.getId() == media.getId());
            }
            mGalleryAdapter.notifyDataSetChanged();
        }
        // 当前选中的如果不是图片，就不限时编辑按钮
        if (PictureMimeType.eqImage(media.getMimeType())) {
            tvEdit.setVisibility(View.VISIBLE);
        } else {
            tvEdit.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onSelectNumChange(boolean isRefresh) {
        if (mPictureSendView == null) {
            return;
        }
        goneParent();
        boolean enable = selectImages.size() != 0;
        if (enable) {
            initCompleteText(selectImages.size());
            if (mRvGallery.getVisibility() == View.GONE) {
                mRvGallery.animate().alpha(1).setDuration(ALPHA_DURATION).setInterpolator(new AccelerateInterpolator());
                mRvGallery.setVisibility(View.VISIBLE);
                bottomLine.animate().alpha(1).setDuration(ALPHA_DURATION).setInterpolator(new AccelerateInterpolator());
                bottomLine.setVisibility(View.VISIBLE);
                // 重置一片内存区域 不然在其他地方添加也影响这里的数量
                mGalleryAdapter.setNewData(selectImages);
            }
            if (config.style != null) {
                if (config.style.pictureCompleteTextColor != 0) {
                    mPictureSendView.setTextColor(config.style.pictureCompleteTextColor);
                }
                if (config.style.pictureCompleteBackgroundStyle != 0) {
                    mPictureSendView.setBackgroundResource(config.style.pictureCompleteBackgroundStyle);
                }
            } else {
                mPictureSendView.setTextColor(ContextCompat.getColor(getContext(), R.color.picture_color_white));
                mPictureSendView.setBackgroundResource(R.drawable.picture_send_button_bg);
            }
        } else {
            if (config.style != null && !TextUtils.isEmpty(config.style.pictureUnCompleteText)) {
                mPictureSendView.setText(config.style.pictureUnCompleteText);
            } else {
                mPictureSendView.setText(getString(R.string.picture_send));
            }
            mRvGallery.animate().alpha(0).setDuration(ALPHA_DURATION).setInterpolator(new AccelerateInterpolator());
            mRvGallery.setVisibility(View.GONE);
            bottomLine.animate().alpha(0).setDuration(ALPHA_DURATION).setInterpolator(new AccelerateInterpolator());
            bottomLine.setVisibility(View.GONE);
        }
    }


    /**
     * 设置完成按钮文字
     */
    @Override
    protected void initCompleteText(int startCount) {
        boolean isNotEmptyStyle = config.style != null;
        if (config.isWithVideoImage) {
            // 混选模式
            if (config.selectionMode == PictureConfig.SINGLE) {
                if (startCount <= 0) {
                    mPictureSendView.setText(isNotEmptyStyle && !TextUtils.isEmpty(config.style.pictureUnCompleteText)
                            ? config.style.pictureUnCompleteText : getString(R.string.picture_send));
                } else {
                    boolean isCompleteReplaceNum = isNotEmptyStyle && config.style.isCompleteReplaceNum;
                    if (isCompleteReplaceNum && isNotEmptyStyle && !TextUtils.isEmpty(config.style.pictureCompleteText)) {
                        mPictureSendView.setText(String.format(config.style.pictureCompleteText, selectImages.size(), 1));
                    } else {
                        mPictureSendView.setText(isNotEmptyStyle && !TextUtils.isEmpty(config.style.pictureCompleteText)
                                ? config.style.pictureCompleteText : getString(R.string.picture_send));
                    }
                }
            } else {
                boolean isCompleteReplaceNum = isNotEmptyStyle && config.style.isCompleteReplaceNum;
                if (isCompleteReplaceNum && isNotEmptyStyle && !TextUtils.isEmpty(config.style.pictureCompleteText)) {
                    mPictureSendView.setText(String.format(config.style.pictureCompleteText,
                            selectImages.size(), config.maxVideoSelectNum + config.maxSelectNum));
                } else {
                    mPictureSendView.setText(isNotEmptyStyle && !TextUtils.isEmpty(config.style.pictureUnCompleteText)
                            ? config.style.pictureUnCompleteText : getString(R.string.picture_send_num, selectImages.size(),
                            config.maxVideoSelectNum + config.maxSelectNum));
                }
            }
        } else {
            String mimeType = selectImages.get(0).getMimeType();
            int maxSize = PictureMimeType.eqVideo(mimeType) ? config.maxVideoSelectNum : config.maxSelectNum;
            if (config.selectionMode == PictureConfig.SINGLE) {
                if (startCount <= 0) {
                    mPictureSendView.setText(isNotEmptyStyle && !TextUtils.isEmpty(config.style.pictureUnCompleteText)
                            ? config.style.pictureUnCompleteText : getString(R.string.picture_send));
                } else {
                    boolean isCompleteReplaceNum = isNotEmptyStyle && config.style.isCompleteReplaceNum;
                    if (isCompleteReplaceNum && isNotEmptyStyle && !TextUtils.isEmpty(config.style.pictureCompleteText)) {
                        mPictureSendView.setText(String.format(config.style.pictureCompleteText, selectImages.size(),
                                1));
                    } else {
                        mPictureSendView.setText(isNotEmptyStyle && !TextUtils.isEmpty(config.style.pictureCompleteText)
                                ? config.style.pictureCompleteText : getString(R.string.picture_send));
                    }
                }
            } else {
                boolean isCompleteReplaceNum = isNotEmptyStyle && config.style.isCompleteReplaceNum;
                if (isCompleteReplaceNum && isNotEmptyStyle && !TextUtils.isEmpty(config.style.pictureCompleteText)) {
                    mPictureSendView.setText(String.format(config.style.pictureCompleteText, selectImages.size(), maxSize));
                } else {
                    mPictureSendView.setText(isNotEmptyStyle && !TextUtils.isEmpty(config.style.pictureUnCompleteText)
                            ? config.style.pictureUnCompleteText
                            : getString(R.string.picture_send_num, selectImages.size(), maxSize));
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult: " + requestCode + " ========== " + resultCode);
        if (requestCode == REQ_IMAGE_EDIT) {
            Log.d(TAG, "onActivityResult: " + images.get(mCurSelectedPosition).getEditPath());

            LocalMedia media = images.get(mCurSelectedPosition);
            if (resultCode == Activity.RESULT_OK) {
                // 刷新大图 ViewPager
                adapter.notifyDataSetChanged();
                // 刷新小图
                for (int i = 0; i < selectImages.size(); i++) {
                    if (selectImages.get(i).getId() == media.getId()) {
                        selectImages.get(i).setEditPath(media.getEditPath());
                        mGalleryAdapter.notifyMediaEdited(i);
                        break;
                    }
                }
            } else {
                media.setEditPath(null);
            }


        }
    }

    @Override
    public void onChangeBarsVisibility() {
        if (selectBarLayout.getVisibility() == View.VISIBLE) {
            selectBarLayout.setVisibility(View.GONE);
            titleViewBg.setVisibility(View.GONE);
            tv_title.setVisibility(View.GONE);
            picture_left_back.setVisibility(View.GONE);
            check.setVisibility(View.GONE);
            btnCheck.setVisibility(View.GONE);
            mPictureSendView.setVisibility(View.GONE);
            mRvGallery.setVisibility(View.GONE);
        } else {
            selectBarLayout.setVisibility(View.VISIBLE);
            titleViewBg.setVisibility(View.VISIBLE);
            tv_title.setVisibility(View.VISIBLE);
            picture_left_back.setVisibility(View.VISIBLE);
            check.setVisibility(View.VISIBLE);
            btnCheck.setVisibility(View.VISIBLE);
            mPictureSendView.setVisibility(View.VISIBLE);
            mRvGallery.setVisibility(View.VISIBLE);
        }
    }
}
