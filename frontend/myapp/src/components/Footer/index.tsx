import { GithubOutlined } from '@ant-design/icons';
import { DefaultFooter} from '@ant-design/pro-components';
import React from 'react';

const Footer: React.FC = () => {
  const currentYear = new Date().getFullYear();
  return (
    <DefaultFooter
      style={{
        background: 'none',
      }}
      copyright={`${currentYear} Build&Create 荣誉出品`}
      links={[
        {
          key: 'Ant Design Pro',
          title: 'Ant Design Pro',
          href: 'https://pro.ant.design',
          blankTarget: true,
        },
        {
          key: 'github',
          title: <GithubOutlined />,
          href: 'https://github.com/Cn-0kr/user_centers',
          blankTarget: true,
        },
        {
          key: 'Build&Create',
          title: 'Build&Create',
          href: 'https://ant.design',
          blankTarget: true,
        },
      ]}
      />
    );
  };
  
  export default Footer;
