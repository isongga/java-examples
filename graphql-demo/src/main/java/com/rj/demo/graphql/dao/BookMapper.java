package com.rj.demo.graphql.dao;

import com.rj.demo.graphql.entity.Book;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

public interface BookMapper {
    @Delete({
        "delete from book",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into book (id, name, ",
        "page_count, author_id)",
        "values (#{id,jdbcType=INTEGER}, #{name,jdbcType=VARCHAR}, ",
        "#{pageCount,jdbcType=INTEGER}, #{authorId,jdbcType=INTEGER})"
    })
    int insert(Book record);

    @Select({
        "select",
        "id, name, page_count, author_id",
        "from book",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="page_count", property="pageCount", jdbcType=JdbcType.INTEGER),
        @Result(column="author_id", property="authorId", jdbcType=JdbcType.INTEGER)
    })
    Book selectByPrimaryKey(Integer id);

    @Select({
        "select",
        "id, name, page_count, author_id",
        "from book"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="page_count", property="pageCount", jdbcType=JdbcType.INTEGER),
        @Result(column="author_id", property="authorId", jdbcType=JdbcType.INTEGER)
    })
    List<Book> selectAll();

    @Update({
        "update book",
        "set name = #{name,jdbcType=VARCHAR},",
          "page_count = #{pageCount,jdbcType=INTEGER},",
          "author_id = #{authorId,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Book record);
}