export default [
  {
    path: '/user',
    layout: false,
    routes: [
      { name: '登录', path: '/user/login', component: './User/Login' },
      { name: '注册', path: '/user/register', component: './User/Register' },
    ],
  },
  { path: '/welcome', name: '欢迎', icon: 'smile', component: './Welcome' },
  {
    path: '/admin',
    name: '管理页',
    icon: 'crown',
    /**
     * 仅管理员可访问
     */
    access: 'canAdmin',
    /**
     * 通过路径/admin就可以访问到Admin组件
     */
    component: './Admin',
    routes: [
      { path: '/admin', redirect: '/admin/user-manage' },
      { path: '/admin/user-manage', name: '用户管理', component: './Admin/UserManage' },
      { path: '/admin/sub-page', name: '二级管理页', component: './Admin' },

    ],
  },
  { name: '查询表格', icon: 'table', path: '/list', component: './TableList' },
  { path: '/', redirect: '/welcome' },
  { path: '*', layout: false, component: './404' },
];
