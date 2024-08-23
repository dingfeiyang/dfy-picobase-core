package io.github.dingfeiyang.model.schema.fieldoptions;


import io.github.dingfeiyang.model.schema.MultiValuer;
import io.github.dingfeiyang.validator.Errors;
import io.github.dingfeiyang.util.PbConstants;
import io.github.dingfeiyang.validator.Validation;

import java.util.List;

public class FileOptions implements FieldOptions, MultiValuer {
    private List<String> mimeTypes;
    private List<String> thumbs;
    private int maxSelect;
    private int maxSize;


    /**
     * 不为该字段生成 get set 方法。
     */
    //@JsonProperty("protected")
    private boolean isProtected;

    /**
     * 用于将form 形式提交过来的 protected 映射到 isProtected上
     *
     * @param isProtected
     */
    public void setProtected(boolean isProtected) {
        this.isProtected = isProtected;
    }

    public boolean getProtected() {
        return this.isProtected;
    }


    //@JsonIgnore
    @Override
    public boolean isMultiple() {
        return this.maxSelect > 1;
    }


    @Override
    public Errors validate() {
        return Validation.validateObject(this,
                Validation.field(FileOptions::getMaxSelect, Validation.required, Validation.min(1)),
                Validation.field(FileOptions::getMaxSize, Validation.required, Validation.min(1)),
                Validation.field(FileOptions::getThumbs, Validation.each(Validation.notIn("0x0", "0x0t", "0x0b", "0x0f"), Validation.match(PbConstants.ThumbSizeRegex)))
        );
    }

    public List<String> getMimeTypes() {
        return mimeTypes;
    }

    public FileOptions setMimeTypes(List<String> mimeTypes) {
        this.mimeTypes = mimeTypes;
        return this;
    }

    public List<String> getThumbs() {
        return thumbs;
    }

    public FileOptions setThumbs(List<String> thumbs) {
        this.thumbs = thumbs;
        return this;
    }

    public int getMaxSelect() {
        return maxSelect;
    }

    public FileOptions setMaxSelect(int maxSelect) {
        this.maxSelect = maxSelect;
        return this;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public FileOptions setMaxSize(int maxSize) {
        this.maxSize = maxSize;
        return this;
    }

}
