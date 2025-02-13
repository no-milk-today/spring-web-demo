CREATE TABLE IF NOT EXISTS "post" (
	"id" serial NOT NULL,
	"title" varchar(50) NOT NULL,
	"image_url" varchar(255) NOT NULL,
	"content" varchar(255) NOT NULL,
	"tag" varchar(50),
	"like_count" bigint NOT NULL,
	"created" timestamp with time zone NOT NULL,
	"updated" timestamp with time zone NOT NULL,
	PRIMARY KEY ("id")
);

CREATE TABLE IF NOT EXISTS "comment" (
	"id" serial NOT NULL,
	"post_id" bigint NOT NULL,
	"content" varchar(255) NOT NULL,
	"author" varchar(50) NOT NULL,
	"created" timestamp with time zone NOT NULL,
	"updated" timestamp with time zone NOT NULL,
	PRIMARY KEY ("id")
);

CREATE TABLE IF NOT EXISTS "post_preview" (
	"post_id" bigint NOT NULL,
	"description" varchar(50) NOT NULL,
	"created" timestamp with time zone NOT NULL,
	"updated" timestamp with time zone NOT NULL,
	PRIMARY KEY ("post_id")
);


ALTER TABLE "comment" ADD CONSTRAINT "comment_fk1" FOREIGN KEY ("post_id") REFERENCES "post"("id");
ALTER TABLE "post_preview" ADD CONSTRAINT "post_preview_fk0" FOREIGN KEY ("post_id") REFERENCES "post"("id");