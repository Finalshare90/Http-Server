
# ***DEPRECATED***
# Check this instead: https://github.com/Finalshare90/Http-Server-Gradle
## Http-Server
A handmade HTTP server made in java using sockets, my 3ml parser and lots of digital masochism.

### Good to know:
- it only uses 1 dependency that you need to compile by hand, my 3ml parser.
- It only supports Web 1.0 pages and is designed to static websites.
- Reserved 3ml tags:
  - port: the port that the sockets uses
  - pages: the "prefix" and the path of the page in your server.
  - files: a pages tag, but for other files but html.
  - index: default page for /
  - notfound: default page for 404
- config.3ml example:
```
[port
5555
end]

[pages
// Your public pages
// your page prefix + his html path
myproducts pages/products.html
news pages/news.html
notaillegalmarket pages/market.html
end]

// No technical reason for it, but i just wanted to make the tag structure looks cool:)
[files
script.js script.js
end]

[index
// Your HTML index file here
pages/anythinghere.html
end]

[notfound
// Default page for 404 message
pages/404.html
end]
```
