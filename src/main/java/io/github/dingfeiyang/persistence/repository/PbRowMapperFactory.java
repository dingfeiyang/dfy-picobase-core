package io.github.dingfeiyang.persistence.repository;

/**
 * 获取默认的 PbRowMapper
 */
@FunctionalInterface
public interface PbRowMapperFactory {

    <T> PbRowMapper<T> getPbRowMapper(Class<T> type);
}
