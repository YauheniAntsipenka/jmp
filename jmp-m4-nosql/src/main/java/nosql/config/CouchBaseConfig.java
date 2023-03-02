package nosql.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;
import org.springframework.data.couchbase.repository.config.EnableCouchbaseRepositories;

/**
 * CouchBaseConfig
 * Date: 02/19/2023
 *
 * @author Yauheni Antsipenka
 */
@Configuration
@EnableCouchbaseRepositories(basePackages={"nosql.repository"})
public class CouchBaseConfig extends AbstractCouchbaseConfiguration {

    private DBProperties dbProp;

    @Autowired
    public void setDbProp(DBProperties dbProp) {
        this.dbProp = dbProp;
    }

    @Override
    public String getConnectionString() {
        return dbProp.getHostName();
    }

    @Override
    public String getUserName() {
        return dbProp.getUsername();
    }

    @Override
    public String getPassword() {
        return dbProp.getPassword();
    }

    @Override
    public String getBucketName() {
        return dbProp.getBucketName();
    }

}
