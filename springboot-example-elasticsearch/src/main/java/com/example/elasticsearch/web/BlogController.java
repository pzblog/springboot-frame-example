package com.example.elasticsearch.web;

import com.example.elasticsearch.model.Blog;
import com.example.elasticsearch.repository.BlogRepository;
import com.example.elasticsearch.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;

@RestController
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    /**
     * 批量添加
     * @param blogs
     * @return
     */
    @PostMapping("/batchAdd")
    public Object add(@RequestBody List<Blog> blogs){
        blogRepository.saveAll(blogs);
        return Result.success();
    }

    /**
     * 添加
     * @param blog
     * @return
     */
    @PostMapping("/add")
    public Object add(@RequestBody Blog blog){
        blogRepository.save(blog);
        return Result.success();
    }

    /**
     * 获取所有信息
     * @return
     */
    @GetMapping("/get")
    public Object getAll(){
        Iterable<Blog> iterable = blogRepository.findAll();
        List<Blog> list = new ArrayList<>();
        iterable.forEach(list :: add);
        return Result.success(list);
    }

    /**
     * 查询指定ID
     * @param id
     * @return
     */
    @GetMapping("/get/{id}")
    public Object getById(@PathVariable String id){
        if(StringUtils.isEmpty(id)){
            return Result.error();
        }
        Optional<Blog> blogOptional = blogRepository.findById(id);
        if(blogOptional.isPresent()){
            Blog blog = blogOptional.get();
            return Result.success(blog);
        }
        return Result.error();
    }

    /**
     * 修改
     * @param blog
     * @return
     */
    @PostMapping("/update")
    public Object updateById(@RequestBody Blog blog){
        if(StringUtils.isEmpty(blog.getId())){
            return Result.error();
        }
        blogRepository.save(blog);
        return Result.success();
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @PostMapping("/delete/{id}")
    public Object deteteById(@PathVariable String id){
        if(StringUtils.isEmpty(id)){
            return Result.error();
        }
        blogRepository.deleteById(id);
        return Result.success();
    }

    /**
     * 普通搜索
     * @param keyword
     * @return
     */
    @GetMapping("/search/title")
    public Object searchTitle(String keyword){
        if(StringUtils.isEmpty(keyword)){
            return Result.error();
        }
        List<Blog> blogs = blogRepository.findByTitleLike(keyword);
        return Result.success(blogs);
    }

    /**
     * 自定义匹配
     * 普通搜索
     * @param keyword
     * @return
     */
    @GetMapping("/search/title/custom")
    public Object searchTitleCustom(String keyword){
        if(StringUtils.isEmpty(keyword)){
            return Result.error();
        }
        List<Blog> blogs = blogRepository.findByTitleCustom(keyword);
        return Result.success(blogs);
    }

    /**
     * 高级搜索
     * @param keyword
     * @return
     */
    @GetMapping("/top/search/title")
    public Object topSearchTitle(String keyword){
        if(StringUtils.isEmpty(keyword)){
            return Result.error();
        }
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(queryStringQuery(keyword))
                .build();

        List<Blog> blogs = elasticsearchTemplate.queryForList(searchQuery, Blog.class);
        return Result.success(blogs);
    }

}
