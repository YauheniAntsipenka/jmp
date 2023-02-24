package nosql.config;

import com.couchbase.client.core.msg.kv.DurabilityLevel;
import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.manager.bucket.BucketSettings;
import com.couchbase.client.java.manager.bucket.BucketType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.repository.config.EnableCouchbaseRepositories;

/**
 * CouchBaseConfig
 * Date: 02/19/2023
 *
 * @author Yauheni Antsipenka
 */
@Configuration
@EnableCouchbaseRepositories(basePackages={"nosql.repository"})
public class CouchBaseConfig {

    private DBProperties dbProp;

    @Autowired
    public void setDbProp(DBProperties dbProp) {
        this.dbProp = dbProp;
    }

    @Bean(destroyMethod = "disconnect")
    public Cluster getCouchbaseCluster() {
        return Cluster.connect(dbProp.getHostName(), dbProp.getUsername(), dbProp.getPassword());
    }

    @Bean
    public Bucket getCouchbaseBucket(Cluster cluster) {
        if (!cluster.buckets().getAllBuckets().containsKey(dbProp.getBucketName())) {
            cluster.buckets().createBucket(
                BucketSettings.create(dbProp.getBucketName())
                    .bucketType(BucketType.COUCHBASE)
                    .minimumDurabilityLevel(DurabilityLevel.NONE)
                    .ramQuotaMB(128));
        }
        return cluster.bucket(dbProp.getBucketName());
    }

}
