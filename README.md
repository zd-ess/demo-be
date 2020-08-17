The task is to develop a campervan JSON API that returns a list of campervans that can be filtered, sorted, and paginated. We have included files to create a database of campervans and campervan images. There is a GOlang project in this repo ready to go, or you can use whatever backend technology you are comfortable with. Finish by pushing your code to Github and deploying both the API to Heroku or another hosting environment.

Your application should respond to the following URLs.

It responds to the next URLs:

http://127.0.0.1:8089/campervans/list
http://127.0.0.1:8089/campervans/priceMinMax?min=16900&max=17001
http://127.0.0.1:8089/campervans/van_paged?pageSize=20&pageNo=1
http://127.0.0.1:8089/campervans/ids?ids=11368,21399,4447
http://127.0.0.1:8089/campervans/campervans/near?lat=21.89&lng=-47.95
http://127.0.0.1:8089/campervans/campervans?sortBy=pricePerDay
http://127.0.0.1:8089/campervans/119960