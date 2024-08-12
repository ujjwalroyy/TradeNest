import React from 'react'
import {
  Table,
  TableBody,
  TableCaption,
  TableCell,
  TableHead,
  TableHeader,
  TableRow,
} from "@/components/ui/table";
import { Avatar, AvatarImage } from "@radix-ui/react-avatar";
const Activity = () => {
  return (
    <div className="p-5 lg:px-20">
      <h1 className='font-bold text-3xl pb-5'>Activity</h1>
      <Table className="border">
        {/* <TableCaption>A list of your recent invoices.</TableCaption> */}
        <TableHeader>
          <TableRow>
            <TableHead className="w-[100px] py-5">Date & Time</TableHead>
            <TableHead>Treading Pair</TableHead>
            <TableHead>Buy Price</TableHead>
            <TableHead>Sell Price</TableHead>
            <TableHead>Order Type</TableHead>
            <TableHead className="text-right">Profit & Loss</TableHead>
            <TableHead className="text-right">Value</TableHead>
          </TableRow>
        </TableHeader>
        <TableBody>
          {[1, 1, 1, 1, 1, 1].map((item, index) => (
            <TableRow key={index}>
              <TableCell>
                <p>2024/05/31</p>
                <p className='text-gray-400'>12:39:32</p>
              </TableCell>
              <TableCell className="font-medium flex item-center gap-2">
                <Avatar className="-z-50">
                  <AvatarImage src="https://e7.pngegg.com/pngimages/26/109/png-clipart-bitcoin-bitcoin-thumbnail.png" />
                </Avatar>
                <span>Bitcoin</span>
              </TableCell>
              <TableCell>9124463121</TableCell>
              <TableCell>912446312145</TableCell>
              <TableCell>-0.20009</TableCell>
              <TableCell className="">$250.00</TableCell>
              <TableCell className="text-right">
              345
              </TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </div>
  )
}

export default Activity
