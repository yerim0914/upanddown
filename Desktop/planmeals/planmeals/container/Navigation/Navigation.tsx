
import './Navigation.css';
import {
    UploadOutlined,
    UserOutlined,
    VideoCameraOutlined,
    MenuOutlined,
    ArrowRightOutlined,
  } from '@ant-design/icons';
import { Layout, Menu, Button, theme } from 'antd';
import { useRouter } from 'next/navigation';
import React, { ReactNode, useState } from 'react';

const { Sider, Content, Footer } = Layout;

interface NavigationProps {
  content: ReactNode;
}

const Navigation: React.FunctionComponent<NavigationProps> = (props) => {
  const [collapsed, setCollapsed] = useState(false);
  const router = useRouter();
  const {
    token: { colorBgContainer },
  } = theme.useToken();
  const listButton: React.CSSProperties = {
    // color: 'blue',
    // float:'inline-end',
    border: 'none',
    width: '50px',
    height: '50px',
  }

  const setCollapsedHandler = () => {
    return collapsed === true ? setCollapsed(false): setCollapsed(true);
  }

  return (
      <Layout rootClassName="NavigationWrapper">
      <Sider trigger={null} collapsible collapsed={collapsed} theme={'light'}>
        <div className="demo-logo-vertical"/>
        <Menu
          theme="dark"
          mode="vertical"
          inlineIndent="20"
          rootClassName="nav-menu"
          // defaultSelectedKeys={['1']}
          items={[
            {
              key: '0',
              label: '냉장고를 부탁해',
              onClick : () => {
                router.push('./');
              }
            },
            {
              key: '1',
              icon: <UserOutlined />,
              label: '추천 요리',
              onClick : () => {
                router.push('./recommend');
              }
            },
            {
              key: '2',
              icon: <VideoCameraOutlined />,
              label: '냉장고 재료',
              onClick : () => {
                router.push('./ingredient');
              }
            },
            {
              key: '3',
              icon: <UploadOutlined />,
              label: '마이페이지',
              onClick : () => {
                router.push('./mypage');
              }
            },
          ]}
        />
      </Sider> 
      <Layout>
        <Content
          className='contentwrapper'
          style={{
            // margin: '24px 16px',
            // padding: 24,
            // minHeight: 280,
            background: colorBgContainer,
          }}
        >
          {props.content}
        </Content>
      </Layout>
    </Layout>
  )
}

export default Navigation;