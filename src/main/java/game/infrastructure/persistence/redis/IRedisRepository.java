package game.infrastructure.persistence.redis;


import game.infrastructure.persistence.redis.generic.IRedisGenericRepository;

/**
 * Created by pengyi on 2016/3/21.
 */
public interface IRedisRepository<K, V> extends IRedisGenericRepository<K, V> {
}
