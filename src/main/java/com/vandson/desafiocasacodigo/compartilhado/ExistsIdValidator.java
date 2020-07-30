package com.vandson.desafiocasacodigo.compartilhado;

import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;
import java.util.List;

/**
 * @author Vandson Lima (vandson.vslima@gmail.com)
 * @since 27/07/2020
 **/
//1
public class ExistsIdValidator implements ConstraintValidator<ExistsId,Object> {
    private Class<?> domainClass;
    private String fildName;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void initialize(ExistsId existsId) {
        domainClass = existsId.domainClass();
        fildName = existsId.fieldName();
    }

    /**
     * Uma entidade é válida quando existe registro no banco com o campo informado
     *
     * @param object
     * @param constraintValidatorContext
     * @return
     */
    @Override
    public boolean isValid(Object object, ConstraintValidatorContext constraintValidatorContext) {
        Query query = entityManager.createQuery("SELECT 1 from "+ domainClass.getName() + " WHERE "+ fildName + "=:attributeValue");
        query.setParameter("attributeValue", object);
        List<?> result = query.getResultList();
        return !result.isEmpty();
    }
}
