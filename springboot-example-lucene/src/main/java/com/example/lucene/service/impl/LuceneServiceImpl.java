package com.example.lucene.service.impl;

import com.example.lucene.model.User;
import com.example.lucene.model.vo.PageQuery;
import com.example.lucene.service.LuceneService;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class LuceneServiceImpl implements LuceneService {

    @Autowired
    private IndexWriter indexWriter;

    @Autowired
    private Analyzer analyzer;

    @Autowired
    private SearcherManager searcherManager;

    @Override
    public void synUserCreatIndex(List<User> userList) throws IOException {
        List<Document> docs = new ArrayList<Document>();
        for (User user : userList) {
            Document doc = new Document();
            doc.add(new StringField("id", user.getId()+"", Field.Store.YES));
            doc.add(new StringField("email", user.getEmail(), Field.Store.YES));
            doc.add(new TextField("nickName", user.getNickName(), Field.Store.YES));
            doc.add(new TextField("regTime", user.getRegTime(), Field.Store.YES));
            doc.add(new TextField("userName", user.getUserName(), Field.Store.YES));
            docs.add(doc);
        }
        indexWriter.addDocuments(docs);
        indexWriter.commit();
    }

    @Override
    public PageQuery<User> searchUser(PageQuery<User> pageQuery) throws IOException, ParseException {
        searcherManager.maybeRefresh();
        IndexSearcher indexSearcher = searcherManager.acquire();
        Query query = new QueryParser("userName", analyzer).parse(pageQuery.getSearchName());
        TopDocs topDocs = indexSearcher.search(query, pageQuery.getPageNum() * pageQuery.getPageSize());
        pageQuery.setTotal(topDocs.totalHits);
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;
        List<User> uList = new ArrayList<User>();
        for (int i = 0; i < scoreDocs.length; i++) {
            Document doc = indexSearcher.doc(scoreDocs[i].doc);
            System.out.println(doc.toString());
            User user = new User();
            user.setId(Long.parseLong(doc.get("id")));
            user.setEmail(doc.get("email"));
            user.setNickName(doc.get("nickName"));
            user.setRegTime(doc.get("regTime"));
            user.setUserName(doc.get("userName"));
            uList.add(user);
        }
        pageQuery.setResults(uList);
        return pageQuery;
    }

    @Override
    public void deleteUserIndexById(String id) throws IOException {
        indexWriter.deleteDocuments(new Term("id",id));
        indexWriter.commit();
    }
}
