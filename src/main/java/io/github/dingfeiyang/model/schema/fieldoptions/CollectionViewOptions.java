package io.github.dingfeiyang.model.schema.fieldoptions;


import io.github.dingfeiyang.validator.Errors;
import io.github.dingfeiyang.validator.Validatable;

import static io.github.dingfeiyang.validator.Validation.*;

// CollectionViewOptions defines the "view" Collection.Options fields.@Data
public class CollectionViewOptions implements Validatable {

    private String query;


    @Override
    public Errors validate() {
        return validateObject(this,
                field(CollectionViewOptions::getQuery, required));
    }

    public String getQuery() {
        return query;
    }

    public CollectionViewOptions setQuery(String query) {
        this.query = query;
        return this;
    }
}
