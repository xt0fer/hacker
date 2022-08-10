# Hacker

a simple HackerNews project in Spring.

## Overall

Build a simple spring container that responds to a request for the Top Ten New news stories from the
`https://news.ycombinator.com` site's API portal.

So it's a spring server that listens for requests, and on each request, the server calls an API service over HTTP, retrieving the latest news stories.

Fortunately, this API does not require the creation of an API key.

## the simple REST stuff in Spring

The very simple POJO, and it's controller. Two URLS, one produces a simple JSON string, the other an HTML response.

## HackerNews Api Service

Inside there is a simple `HackerApiService`

Using the URL `https://hacker-news.firebaseio.com/v0/newstories.json?print=pretty`
to doa HTTP GET, you get a JSON object back, an array of articles. (about 500 long)

```
[ 32399397, 32399396, 32399372, 32399368, 32399354, 32399347, ... 32392588, 32392573 ]
```

Then you break this string down into individual strings like `"32399397"` and put them in
an array(list).

Now, taking each article Id, you ask for an HTTP GET on 

`https://hacker-news.firebaseio.com/v0/item/32399397.json?print=pretty` (see where the article Id is?)

You get a JSON response like 

```
{
  "by" : "dhouston",
  "descendants" : 71,
  "id" : 8863,
  "kids" : [ 32399398, 32399399, 89588884 ],
  "score" : 104,
  "time" : 1175714200,
  "title" : "My YC app: Dropbox - Throw away your USB drive",
  "type" : "story",
  "url" : "http://www.getdropbox.com/u/2/screencast.html"
}
```

And then take the first ten items out of the list of articles, and return each json 'nugget' about each news item in a list.
