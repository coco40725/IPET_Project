package com.ipet.core.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * @author Yu-Jing
 * @create 2023-02-01-上午 11:47
 */
@Repository("mongoTokenRepositoryImpl")
public class MongoTokenRepositoryImpl implements PersistentTokenRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(MongoTokenRepositoryImpl.class);
    private static final String PERSISTENT_COLLETCTION = "persistent_logins";

    private MongoTemplate mongoTemplate;
    @Autowired
    public void setMongoTemplate(MongoTemplate mongoTemplate){
        this.mongoTemplate = mongoTemplate;
    }


    @Override
    public void createNewToken(PersistentRememberMeToken token) {
        System.out.println(token);
        removeUserTokens(token.getUsername()); // 移除舊的
        mongoTemplate.insert(token,PERSISTENT_COLLETCTION);
        LOGGER.info("create [ {} ] TOKEN",token.getUsername());

    }

    @Override
    public void updateToken(String series, String tokenValue, Date lastUsed) {
        Query query = new Query(Criteria.where("series").is(series));
        Update update = new Update();
        update.set("tokenValue",tokenValue);
        update.set("date",lastUsed);
        mongoTemplate.updateFirst(query,update,PERSISTENT_COLLETCTION);
        LOGGER.info("update [ {} ] TOKEN",series);

    }

    @Override
    public PersistentRememberMeToken getTokenForSeries(String seriesId) {
        Query query = new Query(Criteria.where("series").is(seriesId));
        PersistentRememberMeToken one = mongoTemplate.findOne(query, PersistentRememberMeToken.class, PERSISTENT_COLLETCTION);
        LOGGER.info("get [ {} ] TOKEN",one.getSeries());
        return one;
    }

    @Override
    public void removeUserTokens(String username) {
        System.out.println("delete");
        Query username1 = new Query(Criteria.where("username").is(username));
        mongoTemplate.remove(username1, PersistentRememberMeToken.class, PERSISTENT_COLLETCTION);
        LOGGER.info("delete [ {} ] TOKEN",username);

    }
}
