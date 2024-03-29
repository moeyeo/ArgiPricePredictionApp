var vegs = 
    {
      "result":[
        {
          "Name": "Lady Finger Banana",
          "HarvestTime": 12,
          "ImgUrl": "https://cdn.shopify.com/s/files/1/2296/4361/products/banana-ladyfinger_04aee33d-d32b-4c2f-852c-696d3c0eed4f_1080x.jpg?v=1516168624"
        },
        {
          "Name": "Banana",
          "HarvestTime": 12,
          "ImgUrl": "https://www.buyfruit.com.au/images/P/iStock_000011933621Small_%28lady_finger%29__66016.jpg"
        },
        {
          "Name": "Cabbage",
          "HarvestTime": 3,
          "ImgUrl": "https://i5.walmartimages.ca/images/Large/272/109/6000191272109.jpg"
        },
        {
          "Name": "Cauliflower",
          "HarvestTime": 1.5,
          "ImgUrl": "https://i5.walmartimages.ca/images/Large/272/324/6000191272324.jpg"
        },
        {
          "Name": "Dried Roselle",
          "HarvestTime": 3,
          "ImgUrl": "https://cdn.shopify.com/s/files/1/2971/2126/products/Hibiscus_dried_flower_1200x.jpg?v=1530053330"
        },
        {
          "Name": "Baby Corn",
          "HarvestTime": 2,
          "ImgUrl": "https://5.imimg.com/data5/LL/WT/MY-3104590/baby-corn-500x500.png"
        },
        {
          "Name": "Ginger",
          "HarvestTime": 12,
          "ImgUrl": "https://knowyourbodybest.com/wp-content/uploads/2017/05/Ginger-essential-oil-450.jpg"
        },
        {
          "Name": "Jasmine Rice",
          "HarvestTime": 4,
          "ImgUrl": "https://media.buzzle.com/media/images-en/photos/recipes/others/1200-23277025-basmati-rice.jpg"
        },
        {
          "Name": "Celery",
          "HarvestTime": 2,
          "ImgUrl": "https://s3-eu-west-1.amazonaws.com/cdn.webfactore.co.uk/sr_604349_large.png"
        },
        {
          "Name": "Kale",
          "HarvestTime": 2,
          "ImgUrl": "https://cdn.shopify.com/s/files/1/1596/8337/products/chinese-kale-250g_1400x.jpg?v=1539429995"
        },
        {
          "Name": "Water Spinach",
          "HarvestTime": 1,
          "ImgUrl": "https://cdn.shopify.com/s/files/1/1198/2742/products/waterSpinach_b74788d3-7fdd-4ec5-ae29-95a6c52158de_1024x1024.jpg?v=1487414733"
        },
        {
          "Name": "Kinnaree Watermelon",
          "HarvestTime": 4,
          "ImgUrl": "https://newschoolselling.com/wp-content/uploads/2017/08/watermelons-705x564.jpg"
        },
        {
          "Name": "Potato",
          "HarvestTime": 5,
          "ImgUrl": "https://cdn.britannica.com/s:900x675/89/170689-131-D20F8F0A.jpg"
        },
        {
          "Name": "Durian",
          "HarvestTime": "",
          "ImgUrl": "https://i0.wp.com/rawfactoryflavor.com/wp-content/uploads/2015/11/durian.jpg?fit=1100%2C1100&ssl=1"
        },
        {
          "Name": "Radish",
          "HarvestTime": 2,
          "ImgUrl": "https://www.buyitdaily.com/admin/uploads/products/56/1465237364_RADDISH.jpg"
        },
        {
          "Name": "Tomato",
          "HarvestTime": 3,
          "ImgUrl": "https://www.bellavitashop.co.uk/1341-large_default/vine-tomatoes-500gr-350kg.jpg"
        },
        {
          "Name": "Lime",
          "HarvestTime": 6,
          "ImgUrl": "https://www.fruttaweb.com/2227-large_default/fresh-lime.jpg"
        },
        {
          "Name": "Bitter Gourd",
          "HarvestTime": 2,
          "ImgUrl": "https://i0.wp.com/www.vegaproduce.com/wp-content/uploads/2015/07/Chinese-Bitter-Melon.jpg?fit=800%2C800&ssl=1"
        },
        {
          "Name": "Cucumber",
          "HarvestTime": 2,
          "ImgUrl": "http://morungexpress.com/wp-content/uploads/2017/06/87.jpg"
        },
        {
          "Name": "Yardlong Bean",
          "HarvestTime": 2,
          "ImgUrl": "https://veggiesinfo.com/wp-content/uploads/2016/04/yardlong-bean-seeds.jpg"
        },
        {
          "Name": "Coriander",
          "HarvestTime": 2,
          "ImgUrl": "https://cdn.shopify.com/s/files/1/1380/2059/products/Coriander_600x600.jpg?v=1480318423"
        },
        {
          "Name": "Chinese Cabbage",
          "HarvestTime": 2,
          "ImgUrl": "https://5.imimg.com/data5/DK/YY/MY-44851652/chinese-cabbage-500x500.jpg"
        },
        {
          "Name": "Choy",
          "HarvestTime": 2,
          "ImgUrl": "https://www.guineapiggles.co.uk/sites/default/files/styles/scale_720px_wide/public/Food/pak-choi-bok-choy.jpg?itok=CAuQgtcR"
        },
        {
          "Name": "Green Lettuce",
          "HarvestTime": 2,
          "ImgUrl": "https://5.imimg.com/data5/EA/TS/GLADMIN-15285425/lettuce-green-leaves-500x500.png"
        },
        {
          "Name": "Pineapple",
          "HarvestTime": 18,
          "ImgUrl": "https://dhf6qt42idbhy.cloudfront.net/medias/sys_master/h2d/hb4/9458919604254.jpg?buildNumber=0540e9da2b94d5363dcb3cee686c2e42a0cb00f4"
        }
      ]
    };

exports.findAll = function() {
    return vegs;
};

exports.findById = function (id) {
    for (var i = 0; i < vegs.result.length; i++) {
        if (vegs.result[i].Name == id) return vegs.result[i];
    }
};