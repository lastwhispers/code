if redis.call('setnx', KEYS[1], KEYS[2]) == 1
	then return redis.call('expire', KEYS[1], KEYS[3]);
end
    return nil;