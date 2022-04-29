import Vue from 'vue'
import Router from 'vue-router'
// 导入刚才编写的组件
import AppIndex from '@/components/home/AppIndex'
import AppIndex2 from '@/components/home/AppIndex2'
import Login from '@/components/Login'
import AppIndex3 from '@/components/home/AppIndex3'
import AppIndex4 from '@/components/home/AppIndex4'
import Test from '@/components/Test'

Vue.use(Router)

export default new Router({
  routes: [
    // 下面都是固定的写法
    {
      path: '/test',
      name: 'Test',
      component: Test
    },
    {
      path: '/login',
      name: 'Login1',
      component: Login
    },
    {
      path: '/index',
      name: 'AppIndex',
      component: AppIndex
    },
    {
      path: '/index2',
      name: 'AppIndex2',
      component: AppIndex2
    },
    {
      path: '/index3',
      name: 'AppIndex3',
      component: AppIndex3
    },
    {
      path: '/index4',
      name: 'AppIndex3',
      component: AppIndex4
    }
  ]
})
