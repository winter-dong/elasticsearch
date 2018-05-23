package com.dong.elasticsearch;

import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Iterator;

@RestController
public class TestController {

    @Autowired
    private ArticleSearchRepository articleSearchRepository;

    @RequestMapping("/add")
    public void testSaveArticleIndex(){
        Author author = new Author();
        author.setId(2L);
        author.setName("Alice");
        author.setRemark("java developer");

        Tutorial tutorial = new Tutorial();
        tutorial.setId(1L);
        tutorial.setName("elastic search");

        Article article = new Article();
        article.setId(2L);
        article.setTitle("springboot integreate elasticsearch");
        article.setAbstracts("springboot integreate elasticsearch is very easy");
        article.setTutorial(tutorial);
        article.setAuthor(author);
        article.setContent("elasticsearch based on lucene,"
                + "spring-data-elastichsearch based on elaticsearch"
                + ",this tutorial tell you how to integrete springboot with spring-data-elasticsearch");
        article.setPostTime(new Date());
        article.setClickCount(1L);

        articleSearchRepository.save(article);

    }

    @RequestMapping("/query")
    public String testSearch() {
        String queryString = "springboot";//搜索关键字
        QueryStringQueryBuilder builder = new QueryStringQueryBuilder(queryString);
        Iterable<Article> searchResult = articleSearchRepository.search(builder);
        Iterator<Article> iterator = searchResult.iterator();
        String result = null;
        while (iterator.hasNext()) {
            result = iterator.next().toString();
        }
        return result;
    }
}
