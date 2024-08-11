import { Button } from "@/components/ui/button";
import React, { useState } from "react";
import ReactApexChart from "react-apexcharts";

const timeSeries = [
  {
    keyword: "DIGITAL_CURRENCY_DAILY",
    key: "Time Series (Daily)",
    lable: "1 Day",
    value: 1,
  },
  {
    keyword: "DIGITAL_CURRENCY_WEEKLY",
    key: "Weekly Time Series",
    lable: "1 Week",
    value: 7,
  },
  {
    keyword: "DIGITAL_CURRENCY_MONTHLY",
    key: "Monthly Time Series",
    lable: "1 Month",
    value: 30,
  },
];

const StockChart = () => {
  const [activeLable, setActiveLable] = useState("1 Day");

  const searies = [
    {
      data: [
        [1720699447742, 58765.4202988878],
        [1720702862798, 58840.3299279908],
        [1720706880875, 58604.8250547203],
        [1720710567406, 57774.3315544421],
        [1720713864820, 57329.4579401001],
        [1720717341005, 57668.6699406755],
        [1720720804950, 57912.5921068897],
        [1720724965144, 57822.7844005175],
        [1720728631910, 57421.8053345959],
        [1720732012080, 57574.5816491587],
        [1720735431035, 57478.9100911997],
        [1720739228088, 57326.792880218],
        [1720742776153, 57338.4221710998],
        [1720746590228, 57216.4446056535],
        [1720750191443, 56936.1730028706],
        [1720753643821, 57192.4556032727],
        [1720757070942, 56936.7209422467],
      ],
    },
  ];

  const options = {
    chart: {
      id: "area-datetime",
      type: "area",
      height: 350,
      zoom: {
        autoScaleYaxis: true,
      },
    },
    dataLabels: {
      enabled: false,
    },
    xaxis: {
      type: "datetime",
      tickAmount: 6,
    },
    colors: ["#758AA2"],
    markers: {
      colors: ["#fff"],
      strokeColor: "#fff",
      size: 0,
      strokeWidth: 1,
      style: "hollow",
    },
    tooltip: {
      theme: "dark",
    },
    fill: {
      type: "gradient",
      gradient: {
        shadeIntensity: 1,
        opacityFrom: 0.7,
        opacityTo: 0.9,
        stops: [0, 100],
      },
    },
    grid: {
      borderColor: "#47535E",
      strokeDashArray: 4,
      show: true,
    },
  };

  const handleActiveLable = (value) => {
    setActiveLable(value);
  };
  return (
    <div>
      <div className="space-x-3">
        {timeSeries.map((item) => (
          <Button
            variant={activeLable == item.lable ? "" : "outline"}
            onClick={() => handleActiveLable(item.lable)}
            key={item.lable}
          >
            {item.lable}
          </Button>
        ))}
      </div>
      <div id="chart-timelines">
        <ReactApexChart
          options={options}
          series={searies}
          height={450}
          type="area"
        />
      </div>
    </div>
  );
};

export default StockChart;
