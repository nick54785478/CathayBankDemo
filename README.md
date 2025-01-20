<h3>國泰世華 JAVA engineer 線上作業</h3> 

作者: 張耿豪 Nick Zhang

**架構 : MVC 架構**

**後端 : SpirngBoot、Spring Data JPA**

**前端 : 使用 CDN 掛載 Vue.js 搭配 Vuetify**

**資料庫 : h2**

**功能介紹:** 
1. 呼叫 Test 進行測試
* BitcoinFeignClientTest : 透過 Feign Client 取回 Bitcoin 資料
* BitCoinServiceImplTest : 對 Service 進行測試 (依需求打開 @Test 註解)
* CathayBankDemoApplicationTests : 透過 Feign Client 對後端進行集成測試 (註.需先啟動後端專案)

2. 前端頁面(供參): 是我在 2022 年離開聯邦時蝦寫的，應該蠻多 Bug (目前 Vue3 已經忘得差不多了，修改會花太多時間，後續有想要改再改吧)。
Vue3
  * 新增: (點擊後)會出現新增資料功能的 Dialog，輸入並送出。
  * 呼叫coindesk Api: (點擊後)會將資料渲染到頁面上。
  * 依幣別查詢&修改&刪除:
     - 查詢: 根據select框中的Code名稱選取資料，並渲染到前端頁面上。
     - 修改: 根據select框中的Code名稱選取資料，並渲染到 Dialog 的欄位中，使用者可以任意更改內容，並傳回後端。
     - 刪除: 根據select框中的Code名稱選取資料，並渲染到 Dialog 的欄位中(唯讀)，以使用者觀看後決定是否刪除。

**使用流程:**
1.	開啟專案後 => http://localhost:8080/CathayBankDemo/
2.	新增一筆JPY資料(這筆資料在初始化中並未插入h2)。
3.	依上面功能去操作。
