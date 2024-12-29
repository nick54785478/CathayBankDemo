**使用工具: STS4** 

**後端 : SpirngBoot、Spring Data JPA**

**前端 : 使用CDN 掛載Vue.js 搭配 Vuetify**

**資料庫 : h2**

**功能介紹:** 
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
