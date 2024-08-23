package io.github.dingfeiyang.model.schema.fieldoptions;


import io.github.dingfeiyang.model.schema.MultiValuer;
import io.github.dingfeiyang.validator.Err;
import io.github.dingfeiyang.validator.Errors;
import io.github.dingfeiyang.validator.Validation;

public class RelationOptions implements FieldOptions, MultiValuer {

    /**
     * CollectionId is the id of the related collection.
     */
    private String collectionId;

    /**
     * CascadeDelete indicates whether the root model should be deleted
     * in case of delete of all linked relations.
     */
    private boolean cascadeDelete;

    /**
     * MinSelect indicates the min number of allowed relation records
     * that could be linked to the main model.
     * <p>
     * If nil no limits are applied.
     */
    private Integer minSelect;

    /**
     * MaxSelect indicates the max number of allowed relation records
     * that could be linked to the main model.
     * <p>
     * If nil no limits are applied.
     */
    private Integer maxSelect;


    //@JsonIgnore
    @Override
    public boolean isMultiple() {
        return maxSelect == null || maxSelect > 1;
    }


    public RelationOptions(Integer maxSelect, String collectionId) {
        this.maxSelect = maxSelect;
        this.collectionId = collectionId;
    }

    public RelationOptions() {
    }


    @Override
    public Errors validate() {
        int minVal = 0;
        if (minSelect != null) {
            minVal = minSelect;
        }

        return Validation.validateObject(this,
                Validation.field(RelationOptions::getCollectionId, Validation.required),
                Validation.field(RelationOptions::getMinSelect, Validation.min(0)),
                Validation.field(RelationOptions::getMaxSelect, Validation.by(value -> {
                    if(value==null||(Integer)value >1){
                        return null;
                    }
                    return Err.newError("validation_max_select_constraint", "Max select must be greater than 1");
                }), Validation.min(minVal))
        );
    }

    public String getCollectionId() {
        return collectionId;
    }

    public RelationOptions setCollectionId(String collectionId) {
        this.collectionId = collectionId;
        return this;
    }

    public boolean isCascadeDelete() {
        return cascadeDelete;
    }

    public RelationOptions setCascadeDelete(boolean cascadeDelete) {
        this.cascadeDelete = cascadeDelete;
        return this;
    }

    public Integer getMinSelect() {
        return minSelect;
    }

    public RelationOptions setMinSelect(Integer minSelect) {
        this.minSelect = minSelect;
        return this;
    }

    public Integer getMaxSelect() {
        return maxSelect;
    }

    public RelationOptions setMaxSelect(Integer maxSelect) {
        this.maxSelect = maxSelect;
        return this;
    }
}
