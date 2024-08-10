import { Table, TableBody, TableCaption, TableCell, TableHead, TableHeader, TableRow } from '@/components/ui/table'
import { Avatar, AvatarImage } from '@radix-ui/react-avatar'
import React from 'react'

const AssetTable = () => {
  return (
    <Table>
  {/* <TableCaption>A list of your recent invoices.</TableCaption> */}
  <TableHeader>
    <TableRow>
      <TableHead className="w-[100px]">Coin</TableHead>
      <TableHead>SYMBOL</TableHead>
      <TableHead>VOLUME</TableHead>
      <TableHead>MARKET CAP</TableHead>
      <TableHead>24h</TableHead>
      <TableHead className="text-right">PRICE</TableHead>
    </TableRow>
  </TableHeader>
  <TableBody>
    {[1,1,1,1,1,1,1,1,1,1,1,1,1,1].map((item, index) => 
    <TableRow key={index}>
      
    <TableCell className="font-medium flex item-center gap-2">
    <Avatar className='-z-50'>
      <AvatarImage src='https://e7.pngegg.com/pngimages/26/109/png-clipart-bitcoin-bitcoin-thumbnail.png' />
    </Avatar>
    <span>Bitcoin</span>
    </TableCell>
    <TableCell>BTC</TableCell>
    <TableCell>9124463121</TableCell>
    <TableCell>912446312145</TableCell>
    <TableCell>-0.20009</TableCell>
    <TableCell className="text-right">$250.00</TableCell>
  </TableRow>
    )}
    
  </TableBody>
</Table>

  )
}

export default AssetTable
