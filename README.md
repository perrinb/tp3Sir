
Quelles sont les limites d’une base données orientées document ? 
Les bases de données orientée documents sont généralement utilisées pour traiter un gros volume de données.
Cependant, ces bases ont des limites : 
* Moins de fléxibilité qu'un base de données relationnelle.
* L'intégrité des données n'est pas garantie.

Quelles sont les types de données stockés dans Redis, que peut on faire comme types de requêtes ?

(https://redis.io/commands#)
* String
  -> types de requêtes : APPEND, BITCOUNT, BITFIELDS, BITOP, GET, GETRANGE ...
* Listes
  -> types de requêtes : BLPOP, LINDEX, LINSERT, LSET ...
* Hash
  -> types de requêtes : HDEL, HEXISTS, HGET, HKEYS, HSET ...
* Sets
  -> types de requêtes : SADD, SCARD, SDIFF, SINTER ...
* Sets triés
  -> types de requêtes : ZADD, ZCARDS, ZDIFF, ZINTER ...
