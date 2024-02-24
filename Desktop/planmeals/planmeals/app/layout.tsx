'use client';
import './globals.css'
import Navigation from '@/container/Navigation/Navigation';

export default function RootLayout({
  children,
}: {
  children: React.ReactNode
}) {
  return (
    <html lang="ko">
      <body>
        <div className='topwrapper'>
          <Navigation content={children} />
        </div>
        </body>
    </html>
  )
}

