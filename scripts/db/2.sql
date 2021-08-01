\connect stackoverflow;
CREATE TABLE IF NOT EXISTS post_links (
    _Id int,
    _CreationDate timestamp,
    _LinkTypeId int,
    _PostId int,
    _RelatedPostId int,
    PRIMARY KEY(_ID)
);
