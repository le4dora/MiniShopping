<template>
  <el-table
      :data="tableData"
      style="width: 100%"
      show-summary
  >
    <el-table-column type="expand">
      <template #default="{ row }">
        <!-- 使用 row 来绑定当前展开行的数据 -->
        <el-form label-position="left" inline class="demo-table-expand" :model="row">
          <el-form-item label="商品名称">
            <span>{{ row.product_name }}</span>
          </el-form-item>
          <el-form-item label="商品 ID">
            <span>{{ row.id }}</span>
          </el-form-item>
          <el-form-item label="商品分类">
            <span>{{ row.category }}</span>
          </el-form-item>
          <el-form-item label="商品描述">
            <span>{{ row.description }}</span>
          </el-form-item>
        </el-form>
      </template>
    </el-table-column>

    <el-table-column label="商品名称" prop="product_name">
    </el-table-column>

    <el-table-column label="商品状态" prop="state">
    </el-table-column>

    <el-table-column label="商品价格" prop="price">
    </el-table-column>

  </el-table>
</template>

<script>
import instance from "@/request";

export default {
  name: 'MyOrder',
  data() {
    return {
      tableData: [] // 用于存储表格数据
    };
  },
  created() {
    this.fetchData();
  },
  methods: {
    fetchData() {
      instance.get('/product').then(response => {
        // 假设返回的 response.data.data 是包含商品数据的数组
        this.tableData = response.data.data;
        console.log(this.tableData);
      });
    }
  }
}
</script>

<style>
.demo-table-expand {
  font-size: 0;
}
.demo-table-expand label {
  width: 90px;
  color: #99a9bf;
}
.demo-table-expand .el-form-item {
  margin-right: 0;
  margin-bottom: 0;
  width: 50%;
}
</style>
