package io.github.dingfeiyang.model.schema.fieldoptions;


import io.github.dingfeiyang.validator.Errors;

public class EditorOptions implements FieldOptions {
    // ConvertUrls is usually used to instruct the editor whether to
    // apply url conversion (eg. stripping the domain name in case the
    // urls are using the same domain as the one where the editor is loaded).
    //
    // (see also https://www.tiny.cloud/docs/tinymce/6/url-handling/#convert_urls)
    private boolean convertUrls;


    @Override
    public Errors validate() {
        return null;
    }

    public boolean isConvertUrls() {
        return convertUrls;
    }

    public EditorOptions setConvertUrls(boolean convertUrls) {
        this.convertUrls = convertUrls;
        return this;
    }
}
